package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAnswer(

        @Schema(description = "Answer identify", example = "12")
        @NotNull
        Long answer_id,

        @Schema(description = "Answer content", example = "This is the response to the topic, it should not exceed 2000 characters")
        @Size(min = 10, max = 2000, message = "The field must be filled in")
        @NotBlank
        String content,

        @Schema(description = "User identify", example = "3")
        @NotNull(message = "The field must be filled in")
        Long author_id,

        @Schema(description = "Topic identify", example = "6")
        @NotNull(message = "The field must be filled in")
        Long topic_id
) {
}
