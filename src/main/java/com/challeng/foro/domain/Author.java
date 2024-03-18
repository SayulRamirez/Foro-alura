package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public record Author(

        @Schema(description = "User identifier", example = "4")
        Long id,

        @Schema(description = "Name user", example = "Juan Jonson")
        String name
) {
}
