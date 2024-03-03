package com.challeng.foro.domain;

import jakarta.validation.constraints.*;

public record CreateTopic(

        @NotBlank(message = "The field must be filled in")
        @Size(min = 10, max = 100, message = "It should contain between 10 and 100 charactes")
        String title,

        @NotBlank(message = "The field must be filled in")
        @Size(min = 10, max = 2000, message = "It should contain between 10 and 2000 charactes")
        String content,

        @NotNull(message = "The field must be filled in")
        Long author_id,

        @NotNull(message = "The field must be filled in")
        Long courso_id
) {}
