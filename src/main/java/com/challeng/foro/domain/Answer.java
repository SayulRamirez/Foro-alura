package com.challeng.foro.domain;

import java.time.LocalDateTime;

public record Answer(

        String content,
        LocalDateTime answer_date,
        String author
) {


}
