package com.challeng.foro.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestUpdateTopic(

        @NotNull(message = "The field must be filled in")
        Long topic_id,

        @NotBlank(message = "The field must be filled in")
        @Size(min = 10, max = 100, message = "It should contain between 10 and 100 characters")
        String title,

        @NotBlank(message = "The field must be filled in")
        @Size(min = 10, max = 2000, message = "It should contain between 10 and 2000 characters")
        String content,

        @NotNull(message = "The field must be filled in")
        Long author_id
) {
}
