package com.challeng.foro.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAnswer(

        @Size(min = 10, max = 2000, message = "The field must be filled in")
        @NotBlank
        String content,

        @NotNull(message = "The field must be filled in")
        Long author_id,

        @NotNull(message = "The field must be filled in")
        Long topic_id

) {
}
