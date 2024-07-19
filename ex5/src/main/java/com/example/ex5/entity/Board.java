package com.example.ex5.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "writer") //항상 조인되어있는 관계를 느슨하게 만들어줌
public class Board extends BasicEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;

  private String title;
  private String content;

  @ManyToOne( fetch = FetchType.LAZY) //속도를 좀 빠르게 만들어줌
  private Member writer;
  public void changeTitle(String title) {this.title = title;}
  public void changeContent(String content) {
    this.content = content;
  }
}
