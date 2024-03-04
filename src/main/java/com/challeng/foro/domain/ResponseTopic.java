package com.challeng.foro.domain;

import java.time.LocalDateTime;

public record ResponseTopic(

        Long id,
        String title,
        String content,
        LocalDateTime publicationDate,
        String status,
        Author author,
        Course course

) {
}
