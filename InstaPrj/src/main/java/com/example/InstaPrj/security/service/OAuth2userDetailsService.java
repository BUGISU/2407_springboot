package com.example.InstaPrj.security.service;

import com.example.InstaPrj.entity.Member;
import com.example.InstaPrj.entity.MemberRole;
import com.example.InstaPrj.repository.MemberRepository;
import com.example.InstaPrj.security.dto.MemberAuthDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class OAuth2userDetailsService extends DefaultOAuth2UserService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder; //

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.info("============= userRequest: " + userRequest);
    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =
        new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest); //유저 정보를 세션 객체로 변환
    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    SocialType socialType = getSocialType(registrationId.trim().toString());
    String userNameAttributeName = userRequest.getClientRegistration()
        .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
    log.info("userNameAttributeName >> " + userNameAttributeName);
    Map<String, Object> attributes = oAuth2User.getAttributes();
    for (Map.Entry<String, Object> entry : attributes.entrySet()) {
      System.out.println(entry.getKey() + ":" + entry.getValue());
    }
    String email = null;
    if (socialType.name().equals("GOOGLE"))
      email = oAuth2User.getAttribute("email");
    log.info("Email: " + email);
    Member member = saveSocialMember(email);
    MemberAuthDTO memberAuthDTO = new MemberAuthDTO(
        member.getEmail(),
        member.getPassword(),
        member.getMid(),
        true,
        member.getRoleSet().stream().map(
                role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
            .collect(Collectors.toList())
        , attributes
    );
    memberAuthDTO.setFromSocial(member.isFromSocial());
    memberAuthDTO.setName(member.getNickname());
    log.info("clubMemberAuthDTO: " + memberAuthDTO);
    return memberAuthDTO;
  }

  private Member saveSocialMember(String email) {
    Optional<Member> result = memberRepository.findByEmail(email);
    if (result.isPresent()) return result.get();

    Member clubMember = Member.builder()
        .email(email)
        .password(passwordEncoder.encode("1"))
        .fromSocial(true)
        .build();
    clubMember.addMemberRole(MemberRole.USER.USER);
    memberRepository.save(clubMember);
    return clubMember;
  }

  private SocialType getSocialType(String registrationId) {
    if (SocialType.NAVER.name().equals(registrationId)) {
      return SocialType.NAVER;
    }
    if (SocialType.KAKAO.name().equals(registrationId)) {
      return SocialType.KAKAO;
    }
    return SocialType.GOOGLE;
  }

  enum SocialType {
    KAKAO, NAVER, GOOGLE
  }
}

