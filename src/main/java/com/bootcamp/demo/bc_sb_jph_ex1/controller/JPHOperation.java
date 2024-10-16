package com.bootcamp.demo.bc_sb_jph_ex1.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserCommentDto;
import com.bootcamp.demo.bc_sb_jph_ex1.dto.UserDto;

public interface JPHOperation {
  @GetMapping("/users")
  List<UserDto> getUsers();

  @GetMapping("/users/{userId}/comments")
  UserCommentDto getCommentsByUserId(@PathVariable String userId);
}
