package com.example.InstaPrj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "m_member")
public class Member extends BasicEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mid;
  private String nickname;
  private String email;
  private String password;

  private boolean fromSocial;

  @ElementCollection(fetch = FetchType.LAZY)
  @Builder.Default
  private Set<MemberRole> roleSet = new HashSet<>();

  public void addMemberRole(MemberRole memberRole) {
    roleSet.add(memberRole);
  }

}
