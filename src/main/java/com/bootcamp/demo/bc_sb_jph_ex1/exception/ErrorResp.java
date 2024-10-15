package com.bootcamp.demo.bc_sb_jph_ex1.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResp {
  private Integer code;
  private String message;
}
