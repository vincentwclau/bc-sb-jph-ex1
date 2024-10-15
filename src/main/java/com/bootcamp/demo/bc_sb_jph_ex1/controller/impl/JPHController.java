package com.bootcamp.demo.bc_sb_jph_ex1.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.bc_sb_jph_ex1.controller.JPHOperation;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserCommentDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserDTO.PostDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.mapper.JPHMapper;
import com.bootcamp.demo.bc_sb_jph_ex1.model.dto.CommentDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.service.JPHService;

@RestController
public class JPHController implements JPHOperation {
  @Autowired
  private JPHService jphService;

  @Autowired
  private JPHMapper jphMapper;

  @Override
  public List<UserDTO> getUsers() {
    List<com.bootcamp.demo.bc_sb_jph_ex1.model.dto.PostDTO> posts =
        this.jphService.getPosts();

    List<com.bootcamp.demo.bc_sb_jph_ex1.model.dto.CommentDTO> comments =
        this.jphService.getComments();

    return this.jphService.getUsers().stream() //
        .map(user -> {
          UserDTO userDTO = this.jphMapper.map(user);
          List<PostDTO> postDto = posts.stream() //
              .filter(post -> post.getUserId() == user.getId()) //
              .map(post -> {
                
              }).collect(Collectors.toList());
          // userDTO.setPosts(null);

          return new UserDTO();
        }).collect(Collectors.toList());
  }

  @Override
  public UserCommentDTO getCommentsByUserId(String userId) {
    return null;
  }
}
