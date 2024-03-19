package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ResponseTopic(

        @Schema(description = "Topic identify", example = "13")
        Long id,

        @Schema(description = "title topic", example = "Help I don't understand what the code does here")
        String title,

        @Schema(description = "Content topic", example = "System.out.println('Hello word!!');")
        String content,

        @Schema(description = "Date release topic")
        LocalDateTime publicationDate,

        @Schema(description = "Status topic", example = "NO_RESPONDIDO")
        String status,

        Author author,

        Course course

) {
}
