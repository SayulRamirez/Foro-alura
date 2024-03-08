package com.challeng.foro.services;

import com.challeng.foro.domain.Author;
import com.challeng.foro.domain.CreateAnswer;
import com.challeng.foro.domain.ResponseAnswerCreate;
import com.challeng.foro.domain.Topic;
import com.challeng.foro.entities.AnswerEntity;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository repository;

    public AnswerServiceImpl(AnswerRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseAnswerCreate addAnswer(CreateAnswer createAnswer, UserEntity userEntity, TopicEntity topicEntity) {

        AnswerEntity answerEntity = new AnswerEntity
                (null, createAnswer.content(), null, userEntity, null, topicEntity);


        repository.save(answerEntity);

        return new ResponseAnswerCreate(
                answerEntity.getId(),
                answerEntity.getContent(),
                answerEntity.getAnswerDate(),
                new Author(answerEntity.getAuthor().getId(), answerEntity.getAuthor().getName()),
                new Topic(answerEntity.getTopic().getId(), answerEntity.getTopic().getTitle(), answerEntity.getTopic().getMessage())
        );
    }
}
