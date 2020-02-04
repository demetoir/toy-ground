package com.simple.rest_like_api.web;

import com.simple.rest_like_api.domain.post.Posts;
import com.simple.rest_like_api.service.posts.PostsService;
import com.simple.rest_like_api.web.dto.post.PostsListResponseDto;
import com.simple.rest_like_api.web.dto.post.PostsResponseDto;
import com.simple.rest_like_api.web.dto.post.PostsSaveRequestDto;
import com.simple.rest_like_api.web.dto.post.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
  private final PostsService postsService;

  @PostMapping("/api/v1/posts")
  public Long save(@RequestBody PostsSaveRequestDto requestDto) {
    return postsService.save(requestDto);
  }

  @PutMapping("/api/v1/posts/{id}")
  public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
    return postsService.update(id, requestDto);
  }

  @GetMapping("/api/v1/posts/{id}")
  public PostsResponseDto findById(@PathVariable Long id) {
    return postsService.findById(id);
  }

  @GetMapping("/api/v1/posts")
  public PostsListResponseDto findByAll() {
    return postsService.findAll();
  }

  @DeleteMapping("api/v1/posts/{id}")
  public Long deleteById(@PathVariable Long id) {
    return postsService.deleteById(id);
  }
}
