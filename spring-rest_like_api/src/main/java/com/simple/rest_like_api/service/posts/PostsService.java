package com.simple.rest_like_api.service.posts;

import com.simple.rest_like_api.domain.post.Posts;
import com.simple.rest_like_api.domain.post.PostsRepository;
import com.simple.rest_like_api.web.dto.post.PostsSaveRequestDto;
import com.simple.rest_like_api.web.dto.post.PostsResponseDto;
import com.simple.rest_like_api.web.dto.post.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;

  @Transactional
  public long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity()).getId();
  }

  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto) {
    Posts posts =
        postsRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException(" 사용자 없어 id=" + id));

    posts.update(requestDto.getTitle(), requestDto.getContent());

    return id;
  }

  public PostsResponseDto findById(Long id) {
    Posts entity =
        postsRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException(" 사용자 없어 id=" + id));

    return new PostsResponseDto(entity);
  }
}
