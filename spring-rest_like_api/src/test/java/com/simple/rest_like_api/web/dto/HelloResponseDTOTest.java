package com.simple.rest_like_api.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDTOTest {

    @Test
    public void ableToConstruct() {
        String name = "hello";
        int number = 1;

        HelloResponseDTO dto = new HelloResponseDTO(name, number);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getNumber()).isEqualTo(number);
    }

}