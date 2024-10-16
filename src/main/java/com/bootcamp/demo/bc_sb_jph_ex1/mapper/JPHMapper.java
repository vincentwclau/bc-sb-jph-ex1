package com.bootcamp.demo.bc_sb_jph_ex1.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserCommentDto;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserDto;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserDto.PostDto;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserDto.PostDto.CommentDto;
import com.bootcamp.demo.bc_sb_jph_ex1.model.dto.CommentDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.model.dto.PostDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.model.dto.UserDTO;

@Component
public class JPHMapper {
  public UserDto map(UserDTO user, List<PostDto> postDtos) {
    return UserDto.builder() //
        .id(user.getId()) //
        .name(user.getName()) //
        .username(user.getUsername()) //
        .email(user.getEmail()) //
        .address(this.map(user.getAddress())) //
        .phone(user.getPhone()) //
        .website(user.getWebsite()) //
        .company(this.map(user.getCompany())) //
        .posts(postDtos) //
        .build();
  }

  public UserDto.Company map(UserDTO.Company company) {
    return UserDto.Company.builder() //
        .name(company.getName()) //
        .bs(company.getBs()) //
        .catchPhrase(company.getCatchPhrase()) //
        .build();
  }

  public UserDto.Address map(UserDTO.Address address) {
    return UserDto.Address.builder() //
        .city(address.getCity()) //
        .street(address.getStreet()) //
        .suite(address.getSuite()) //
        .zipcode(address.getZipcode()) //
        .geo(this.map(address.getGeo())) //
        .build();
  }

  public UserDto.Address.Geo map(UserDTO.Address.Geo geo) {
    return UserDto.Address.Geo.builder() //
        .lat(geo.getLat()) //
        .lng(geo.getLng()) //
        .build();
  }

  public PostDto map(PostDTO post, List<CommentDto> comments) {
    return PostDto.builder() //
        .id(post.getId()) //
        .title(post.getTitle()) //
        .body(post.getBody()) //
        .comments(comments) //
        .build();
  }

  public UserDto.PostDto.CommentDto map(CommentDTO comment) {
    return CommentDto.builder() //
        .id(comment.getId()) //
        .name(comment.getName()) //
        .email(comment.getEmail()) //
        .body(comment.getBody()) //
        .build();
  }

  public UserCommentDto.CommentDto map2(CommentDTO comment) {
    return UserCommentDto.CommentDto.builder() //
        .name(comment.getName()) //
        .email(comment.getEmail()) //
        .body(comment.getBody()) //
        .build();
  }

}
