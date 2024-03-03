package com.challeng.foro.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTopic(

        @NotBlank(message = "The field must be filled in")
        @Size(min = 20, max = 100, message = "It should contain between 20 and 100 charactes")
        String title,

        @NotBlank(message = "The field must be filled in")
        @Size(min = 50, max = 2000, message = "It should contain between 50 and 2000 charactes")
        String content,

        @NotBlank(message = "The field must be filled in")
        Long author_id,

        @NotBlank(message = "The field must be filled in")
        Long courso_id
) {}
