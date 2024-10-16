package com.bootcamp.demo.bc_sb_jph_ex1.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.bc_sb_jph_ex1.exception.BusinessException;
import com.bootcamp.demo.bc_sb_jph_ex1.exception.ErrorCode;
import com.bootcamp.demo.bc_sb_jph_ex1.model.dto.CommentDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.model.dto.PostDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.model.dto.UserDTO;
import com.bootcamp.demo.bc_sb_jph_ex1.service.JPHService;
import com.bootcamp.demo.bc_sb_jph_ex1.util.Scheme;
import com.bootcamp.demo.bc_sb_jph_ex1.util.UrlManager;

@Service
public class JPHServiceImpl implements JPHService {

  private final String errorMsg = "RestTemplate Error - JsonPlaceHolder";

  @Autowired
  private RestTemplate restTemplate;

  @Value("${api.jph.domain}")
  private String jphDomain;

  @Value("${api.jph.endpoints.users}")
  private String usersEndpoint;

  @Value("${api.jph.endpoints.posts}")
  private String postsEndpoint;

  @Value("${api.jph.endpoints.comments}")
  private String commentsEndpoint;

  @Override
  public List<UserDTO> getUsers() {
    String url = UrlManager.builder() //
        .scheme(Scheme.HTTPS) //
        .domain(this.jphDomain) //
        .endpoint(this.usersEndpoint) //
        .build() //
        .toUriString();
    try {
      return Arrays.asList(this.restTemplate.getForObject(url, UserDTO[].class));
    } catch (RestClientException e) {
      throw BusinessException.of(ErrorCode.REST_CLIENT_EX, this.errorMsg);
    }
  }

  @Override
  public List<PostDTO> getPosts() {
    String url = UrlManager.builder() //
        .scheme(Scheme.HTTPS) //
        .domain(this.jphDomain) //
        .endpoint(this.postsEndpoint) //
        .build() //
        .toUriString();
    try {
      return Arrays.asList(this.restTemplate.getForObject(url, PostDTO[].class));
    } catch (RestClientException e) {
      throw BusinessException.of(ErrorCode.REST_CLIENT_EX, this.errorMsg);
    }
  }

  @Override
  public List<CommentDTO> getComments() {
    String url = UrlManager.builder() //
        .scheme(Scheme.HTTPS) //
        .domain(this.jphDomain) //
        .endpoint(this.commentsEndpoint) //
        .build() //
        .toUriString();
    try {
      return Arrays.asList(this.restTemplate.getForObject(url, CommentDTO[].class));
    } catch (RestClientException e) {
      throw BusinessException.of(ErrorCode.REST_CLIENT_EX, this.errorMsg);
    }
  }
}
