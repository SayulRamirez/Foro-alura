package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record CreateTopic(

        @Schema(description = "Title a topic", example = "This is title the topic")
        @NotBlank(message = "The field must be filled in")
        @Size(min = 10, max = 100, message = "It should contain between 10 and 100 characters")
        String title,

        @Schema(description = "Content topic", example = "Here are the doubts, problems or contributions on the topic.")
        @NotBlank(message = "The field must be filled in")
        @Size(min = 10, max = 2000, message = "It should contain between 10 and 2000 characters")
        String content,

        @Schema(description = "User identify", example = "21")
        @NotNull(message = "The field must be filled in")
        Long author_id,

        @Schema(description = "Course identify", example = "19")
        @NotNull(message = "The field must be filled in")
        Long course_id
) {}
