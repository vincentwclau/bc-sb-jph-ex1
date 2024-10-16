package com.bootcamp.demo.bc_sb_jph_ex1.controller.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.bc_sb_jph_ex1.controller.JPHOperation;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserCommentDto;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserDto;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserDto.PostDto;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserDto.PostDto.CommentDto;
import com.bootcamp.demo.bc_sb_jph_ex1.exception.BusinessException;
import com.bootcamp.demo.bc_sb_jph_ex1.exception.ErrorCode;
import com.bootcamp.demo.bc_sb_jph_ex1.mapper.JPHMapper;
import com.bootcamp.demo.bc_sb_jph_ex1.model.dto.CommentDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.model.dto.PostDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.model.dto.UserDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.service.JPHService;

@RestController
public class JPHController implements JPHOperation {
  @Autowired
  private JPHService jphService;

  @Autowired
  private JPHMapper jphMapper;

  @Override
  public List<UserDto> getUsers() {
    List<UserDTO> usersData = this.jphService.getUsers();
    List<PostDTO> postsData = this.jphService.getPosts();
    List<CommentDTO> commentsData = this.jphService.getComments();
    return usersData.stream() //
        .map(u -> {
          List<PostDto> postDtos = postsData.stream() //
              .filter(p -> p.getUserId() == u.getId()) //
              .map(p -> {
                List<CommentDto> commentDtos = commentsData.stream() //
                    .filter(c -> c.getPostId() == p.getId()) //
                    .map(c -> this.jphMapper.map(c)) //
                    .collect(Collectors.toList());
                return this.jphMapper.map(p, commentDtos);
              }).collect(Collectors.toList());
          return this.jphMapper.map(u, postDtos);
        }).collect(Collectors.toList());
  }

  @Override
  public UserCommentDto getCommentsByUserId(String userId) {
    Long uid;
    try {
      uid = Long.parseLong(userId);
    } catch (NumberFormatException e) {
      throw BusinessException.of(ErrorCode.INV_INPUT_EX);
    }
    List<UserDTO> usersData = this.jphService.getUsers();
    Optional<UserDTO> oUser = usersData.stream() //
        .filter(u -> u.getId().equals(uid)) //
        .findAny();
    if (!oUser.isPresent())
      throw BusinessException.of(ErrorCode.USER_NOT_FOUND_EX);

    List<PostDTO> postsData = this.jphService.getPosts();
    List<CommentDTO> commentsData = this.jphService.getComments();

    List<UserCommentDto.CommentDto> comments = postsData.stream() //
        .filter(p -> p.getId().equals(uid)) //
        .flatMap(p -> commentsData.stream()
            .filter(c -> c.getPostId().equals(p.getId()))
            .map(c -> this.jphMapper.map2(c)))
        .collect(Collectors.toList());
    return UserCommentDto.builder() //
        .userId(oUser.get().getId()) //
        .username(oUser.get().getUsername()) //
        .comments(comments) //
        .build();
  }
}
