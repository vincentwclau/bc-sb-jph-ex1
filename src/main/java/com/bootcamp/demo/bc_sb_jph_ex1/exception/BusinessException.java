package com.bootcamp.demo.bc_sb_jph_ex1.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private int code;

  public BusinessException(ErrorCode error) {
    super(error.getMessage());
    this.code = error.getCode();
  }

  public BusinessException(ErrorCode error, String overrideMessage) {
    super(overrideMessage);
    this.code = error.getCode();
  }
}