package com.challeng.foro.services;

import com.challeng.foro.domain.*;
import com.challeng.foro.entities.AnswerEntity;
import com.challeng.foro.entities.StatusEntity;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.repositories.AnswerRepository;
import com.challeng.foro.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository repository;
    private final TopicRepository topicRepository;

    public AnswerServiceImpl(AnswerRepository repository, TopicRepository topicRepository) {
        this.repository = repository;
        this.topicRepository = topicRepository;
    }

    @Override
    public ResponseAnswerCreate addAnswer(CreateAnswer createAnswer, UserEntity userEntity, TopicEntity topicEntity) {

        AnswerEntity answerEntity = new AnswerEntity
                (null, createAnswer.content(), null, userEntity, topicEntity);

        repository.save(answerEntity);

        if (topicEntity.getAnswers().size() == 1) {
            topicEntity.setStatus(new StatusEntity(2, null));
            topicRepository.save(topicEntity);
        }

        return new ResponseAnswerCreate(
                answerEntity.getId(),
                answerEntity.getContent(),
                answerEntity.getAnswerDate(),
                new Author(answerEntity.getAuthor().getId(), answerEntity.getAuthor().getName()),
                new Topic(answerEntity.getTopic().getId(), answerEntity.getTopic().getTitle(), answerEntity.getTopic().getMessage())
        );
    }

    @Override
    public List<Answer> allAnswers(Long id) {

        TopicEntity topicEntity = topicRepository.findById(id).orElse(null);

        if (topicEntity == null) return null;

        List<AnswerEntity> answerEntities = topicEntity.getAnswers();

        List<Answer> answers = new ArrayList<>();
        answerEntities.forEach(a -> answers.add(new Answer(a.getContent(), a.getAnswerDate(), a.getAuthor().getName())));

        return answers;
    }

    @Override
    public Answer edit(UpdateAnswer updateAnswer) {

        AnswerEntity answerEntity = repository.findById(updateAnswer.answer_id()).orElse(null);

        if (answerEntity == null
                || !answerEntity.getAuthor().getId().equals(updateAnswer.author_id())
                || !answerEntity.getTopic().getId().equals(updateAnswer.topic_id())) return null;

        answerEntity.setContent(updateAnswer.content());

        repository.save(answerEntity);

        return new Answer(answerEntity.getContent(), answerEntity.getAnswerDate(), answerEntity.getAuthor().getName());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
