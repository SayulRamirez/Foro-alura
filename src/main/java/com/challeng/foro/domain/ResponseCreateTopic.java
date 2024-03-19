package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ResponseCreateTopic(

        @Schema(description = "Topic identifier", example = "12")
        Long id,

        @Schema(description = "Title topic", example = "Help I don't understand what the code does here")
        String title,

        @Schema(description = "Content topic", example = "System.out.println('Hello word!!');")
        String content,

        @Schema(description = "Date release topic")
        LocalDateTime publicationDate,

        Author author,

        Course course
) {
}
