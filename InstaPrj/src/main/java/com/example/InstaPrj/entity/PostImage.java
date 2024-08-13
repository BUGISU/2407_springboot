package com.example.InstaPrj.entity;

import jakarta.persistence.*;

public class PostImage extends BasicEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long inum;
  private String uuid; //Universally Unique IDentifier
  private String imgName;
  private String path;
  @ManyToOne(fetch = FetchType.LAZY)
  private Post post;
}
