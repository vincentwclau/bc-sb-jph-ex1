package com.bootcamp.demo.bc_sb_jph_ex1.model.dto;

import lombok.Getter;

@Getter
public class CommentDTO {
  private Long postId;
  private Long id;
  private String name;
  private String email;
  private String body;
}
