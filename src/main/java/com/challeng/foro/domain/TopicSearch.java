package com.challeng.foro.domain;

import java.time.LocalDateTime;

public record TopicSearch(
        Long id,
        String title,
        LocalDateTime date
) {
}
