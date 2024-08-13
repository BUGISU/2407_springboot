package com.example.InstaPrj.security.service;

import com.example.InstaPrj.entity.Member;
import com.example.InstaPrj.repository.MemberRepository;
import com.example.InstaPrj.security.dto.MemberAuthDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor //DB접근방식으로 UserDetailsService(인증 관리 객체) 사용
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  // DB에 있는 것 확인 된후,User를 상속받은 MemberAuthDTO에 로그인정보를 담음=>세션
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("MemberUser.........", username);
    Optional<Member> result = memberRepository.findByEmail(username);
    if (!result.isPresent()) throw new UsernameNotFoundException("Check Email or Social");
    Member member = result.get(); //DB로 부터 검색한, Entity
    // 엔티티를 세션으로 담기위해 만든 MemberAuthDTO
    MemberAuthDTO memberAuthDTO = new MemberAuthDTO(
        member.getEmail(), member.getPassword(), member.getMid(),
        member.isFromSocial(),
        member.getRoleSet().stream().map(
            clubMemberRole -> new SimpleGrantedAuthority(
                "ROLE_" + clubMemberRole.name())).collect(Collectors.toList())
    );
    memberAuthDTO.setName(member.getNickname());
    memberAuthDTO.setFromSocial(member.isFromSocial());
    log.info("memberAuthDTO >> ", memberAuthDTO.getMid());
    return memberAuthDTO;
  }
}
