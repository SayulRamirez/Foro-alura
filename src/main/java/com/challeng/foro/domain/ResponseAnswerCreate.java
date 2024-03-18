package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ResponseAnswerCreate(

        @Schema(description = "User identifier", example = "1")
        Long answer_id,

        @Schema(description = "Message content", example = "This is the answer, whatever.")
        String content,

        @Schema(description = "Date create answer", example = "2024-03-18 05:53:36.66")
        LocalDateTime answer_date,

        Author author,

        Topic topic
) {
}
