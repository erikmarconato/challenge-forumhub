package com.alura.challenge_forumhub.services;

import com.alura.challenge_forumhub.dtos.TopicDetailsDto;
import com.alura.challenge_forumhub.dtos.TopicDetailsUpdateDto;
import com.alura.challenge_forumhub.dtos.TopicRegistrationDto;
import com.alura.challenge_forumhub.entities.TopicEntity;
import com.alura.challenge_forumhub.enums.TopicStatusEnum;
import com.alura.challenge_forumhub.exceptions.TopicNotFoundException;
import com.alura.challenge_forumhub.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public ResponseEntity<TopicDetailsDto> saveTopic(TopicRegistrationDto topicRegistrationDto) {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setTitle(topicRegistrationDto.title());
        topicEntity.setMessage(topicRegistrationDto.message());
        topicEntity.setCreationDate(LocalDateTime.now());
        topicEntity.setStatus(TopicStatusEnum.NAO_RESPONDIDO);
        topicEntity.setCurse(topicRegistrationDto.curse());

        topicRepository.save(topicEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TopicDetailsDto(
                topicEntity.getId(),
                topicEntity.getTitle(),
                topicEntity.getMessage(),
                topicEntity.getCreationDate(),
                topicEntity.getStatus(),
                topicEntity.getCurse(),
                topicEntity.getAnswers()
        ));
    }

    public ResponseEntity<List<TopicDetailsDto>> findAllTopicsActive (){
        List<TopicEntity> topicEntityList = topicRepository.findAllStatusActive();

        return ResponseEntity.ok(topicEntityList.stream().map(t ->
                new TopicDetailsDto(
                        t.getId(),
                        t.getTitle(),
                        t.getMessage(),
                        t.getCreationDate(),
                        t.getStatus(),
                        t.getCurse(),
                        t.getAnswers()
                )
        ).toList());
    }

    public ResponseEntity<Optional<TopicDetailsDto>> findTopicById(Long id){
        Optional<TopicEntity> topicEntity = topicRepository.findById(id);

        if (topicEntity.isEmpty()) {
            throw new TopicNotFoundException("Tópico não encontrado com o ID: " + id);
        }

        return ResponseEntity.ok(topicEntity.map(
                t -> new TopicDetailsDto(
                        t.getId(),
                        t.getTitle(),
                        t.getMessage(),
                        t.getCreationDate(),
                        t.getStatus(),
                        t.getCurse(),
                        t.getAnswers()
                )));
    }

    public ResponseEntity<Void> deleteTopicById(Long id){
        Optional<TopicEntity> topic = topicRepository.findById(id);

        if (topic.isEmpty() || topic.get().getStatus() == TopicStatusEnum.DELETADO) {
            throw new TopicNotFoundException("Tópico não encontrado ou já excluído.");
        }

        topic.get().setStatus(TopicStatusEnum.DELETADO);

        topicRepository.save(topic.get());

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<TopicDetailsUpdateDto> updateTopic (Long id, TopicRegistrationDto editTopic){
        Optional<TopicEntity> topic = topicRepository.findById(id);

        if (topic.isEmpty() || topic.get().getStatus() == TopicStatusEnum.DELETADO) {
            throw new TopicNotFoundException("Tópico não encontrado ou já excluído.");
        }

        topic.get().setTitle(editTopic.title());
        topic.get().setMessage(editTopic.message());
        topic.get().setUpdateDate(LocalDateTime.now());
        topic.get().setCurse(editTopic.curse());

        topicRepository.save(topic.get());

        return ResponseEntity.ok(new TopicDetailsUpdateDto(
                topic.get().getId(),
                topic.get().getTitle(),
                topic.get().getMessage(),
                topic.get().getCreationDate(),
                topic.get().getUpdateDate(),
                topic.get().getStatus(),
                topic.get().getCurse(),
                topic.get().getAnswers()
        ));
    }
}
