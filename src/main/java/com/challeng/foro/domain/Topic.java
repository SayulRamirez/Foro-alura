package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public record Topic(

        @Schema(description = "Topic identifier", example = "4")
        Long id,

        @Schema(description = "Title topic", example = "Help! I don't understand notting.")
        String title,

        @Schema(description = "Content message topic", example = "The description of the topic's problem, any questions or really anything.")
        String content) {
}
