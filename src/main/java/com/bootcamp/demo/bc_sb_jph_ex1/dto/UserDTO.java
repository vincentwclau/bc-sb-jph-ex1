package com.bootcamp.demo.bc_sb_jph_ex1.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserDTO {
  private int id;
  private String name;
  private String username;
  private String email;
  private Address address;
  @Setter
  List<PostDTO> posts;

  @Getter
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    public static class Geo {
      private String lat;
      private String lng;
    }
  }

  @Getter
  public static class PostDTO {
    private int id;
    private String title;
    private String body;
    @Setter
    List<CommentDTO> comments;

    @Getter
    public class CommentDTO {
      private int id;
      private String name;
      private String email;
      private String body;
    }
  }
}
