//src/main/java/com/example/ex4/entity/Guestbook.java
package com.example.ex4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BasicEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long gno;
  @Column(length = 100, nullable = false)
  private String title;
  @Column(length = 1500, nullable = false)
  private String content;
  @Column(length = 50, nullable = false)
  private String writer;
}
