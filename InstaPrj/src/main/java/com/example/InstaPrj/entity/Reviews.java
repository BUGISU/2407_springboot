package com.example.InstaPrj.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"feeds", "member"})
public class Reviews extends BasicEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reviewnum;
  @ManyToOne(fetch = FetchType.LAZY)
  private Feeds feeds;
  @ManyToOne(fetch = FetchType.LAZY)
  private ClubMember clubMember;
  private int grade; //별점
  private String text; //한줄평
  public void changeGrade(int grade) {this.grade = grade;}
  public void changeText(String text) {this.text = text;}
}