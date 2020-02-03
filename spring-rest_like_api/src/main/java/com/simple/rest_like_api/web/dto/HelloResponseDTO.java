package com.simple.rest_like_api.web.dto;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

@Getter
@RequiredArgsConstructor
public class HelloResponseDTO {
    private final String name;
    private final int number;
    private boolean isDone;
}
