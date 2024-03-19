package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public record Course(

        @Schema(description = "Name course", example = "Object-oriented course with java")
        String name,

        @Schema(description = "Category course", example = "Java")
        String category
) {
}
