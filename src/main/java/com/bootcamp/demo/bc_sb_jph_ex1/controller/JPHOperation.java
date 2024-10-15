package com.bootcamp.demo.bc_sb_jph_ex1.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserCommentDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserDTO;

public interface JPHOperation {
  @GetMapping("/users")
  List<UserDTO> getUsers();

  @GetMapping("/users/{userId}/comments")
  UserCommentDTO getCommentsByUserId(@PathVariable String userId);
}
