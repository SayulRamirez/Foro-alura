package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record DetailedTopic(

        @Schema(description = "Topic identifier", example = "14")
        Long id,

        @Schema(description = "Title topic", example = "Help I don't understand what the code does here")
        String title,

        @Schema(description = "Content topic", example = "System.out.println('Hello word!!');")
        String content,

        @Schema(description = "Date release topic")
        LocalDateTime publicationDate,

        @Schema(description = "Status topic", example = "NO_SOLUCIONADO")
        String status,

        Author author,

        Course course,

        List<Answer> answer
) {
}
