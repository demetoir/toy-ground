package com.example.demetoir.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;

public class PageVO {
  private static final int DEFAULT_SIZE = 10;

  private static final int DEFAULT_MAX_SIZE = 50;

  private int page;
  private int size;

  @Setter
  @Getter
  private String keyword;

  @Getter
  @Setter
  private String type;

  public PageVO() {
    this.page = 1;
    this.size = DEFAULT_SIZE;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page < 0 ? 1 : page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = Math.min(size, DEFAULT_MAX_SIZE);
  }

  public Pageable makePageable(int direction, String... props) {
    Sort.Direction dir = direction == 0 ? Sort.Direction.DESC : Sort.Direction.ASC;

    return PageRequest.of(this.page - 1, this.size, dir, props);
  }
}
