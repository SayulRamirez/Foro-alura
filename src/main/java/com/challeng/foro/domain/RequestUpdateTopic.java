package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestUpdateTopic(

        @Schema(description = "Topic identifier", example = "1")
        @NotNull(message = "The field must be filled in")
        Long topic_id,

        @Schema(description = "Title topic", example = "Help I don't understand what the code does here")
        @NotBlank(message = "The field must be filled in")
        @Size(min = 10, max = 100, message = "It should contain between 10 and 100 characters")
        String title,

        @Schema(description = "Content topic", example = "System.out.println('Hello word!!');")
        @NotBlank(message = "The field must be filled in")
        @Size(min = 10, max = 2000, message = "It should contain between 10 and 2000 characters")
        String content,

        @Schema(description = "User identifier", example = "14")
        @NotNull(message = "The field must be filled in")
        Long author_id
) {
}
