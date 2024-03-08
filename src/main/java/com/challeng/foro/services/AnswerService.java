package com.challeng.foro.services;

import com.challeng.foro.domain.CreateAnswer;
import com.challeng.foro.domain.ResponseAnswerCreate;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.entities.UserEntity;

public interface AnswerService {

    /**
     * Add new answer
     * @param createAnswer data answer
     * @param userEntity author
     * @param topicEntity topic
     * @return ResponseAnswerCreate
     */
    ResponseAnswerCreate addAnswer(CreateAnswer createAnswer, UserEntity userEntity, TopicEntity topicEntity);
}
