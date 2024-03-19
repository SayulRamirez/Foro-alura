package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record TopicSearch(

        @Schema(description = "Topic identifier", example = "15")
        Long id,

        @Schema(description = "Title topic", example = "Help, dont understand")
        String title,

        @Schema(description = "Date release topic")
        LocalDateTime date) {
}
