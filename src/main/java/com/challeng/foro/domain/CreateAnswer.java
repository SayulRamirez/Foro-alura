package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAnswer(

        @Schema(description = "Content the answers", example = "This is the solution at topic")
        @Size(min = 10, max = 2000, message = "The field must be filled in")
        @NotBlank
        String content,

        @Schema(description = "User identifier", example = "2")
        @NotNull(message = "The field must be filled in")
        Long author_id,

        @Schema(description = "Topic identifier", example = "1")
        @NotNull(message = "The field must be filled in")
        Long topic_id
) {
}
