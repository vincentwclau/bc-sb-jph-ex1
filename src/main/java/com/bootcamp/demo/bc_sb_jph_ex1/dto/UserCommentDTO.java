package com.bootcamp.demo.bc_sb_jph_ex1.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class UserCommentDTO {
  private int userId;
  private String username;
  private List<CommentDTO> comments;

  @Getter
  public class CommentDTO {
    private String name;
    private String email;
    private String body;
  }
}
