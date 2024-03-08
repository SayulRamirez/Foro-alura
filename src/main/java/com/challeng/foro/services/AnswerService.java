package com.challeng.foro.services;

import com.challeng.foro.domain.Answer;
import com.challeng.foro.domain.CreateAnswer;
import com.challeng.foro.domain.ResponseAnswerCreate;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.entities.UserEntity;

import java.util.List;

public interface AnswerService {

    /**
     * Add new answer
     * @param createAnswer data answer
     * @param userEntity author
     * @param topicEntity topic
     * @return ResponseAnswerCreate
     */
    ResponseAnswerCreate addAnswer(CreateAnswer createAnswer, UserEntity userEntity, TopicEntity topicEntity);


    /**
     * List the answers from a only topic
     * @param id {@link Long}
     * @return Answer
     */
    List<Answer> allAnswers(Long id);
}
