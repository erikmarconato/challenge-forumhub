package com.alura.challenge_forumhub.services;

import com.alura.challenge_forumhub.dtos.TopicDetailsDto;
import com.alura.challenge_forumhub.dtos.TopicDetailsUpdateDto;
import com.alura.challenge_forumhub.dtos.TopicRegistrationDto;
import com.alura.challenge_forumhub.entities.TopicEntity;
import com.alura.challenge_forumhub.entities.UserEntity;
import com.alura.challenge_forumhub.enums.TopicStatusEnum;
import com.alura.challenge_forumhub.infra.exceptions.TopicNotFoundException;
import com.alura.challenge_forumhub.infra.exceptions.UnauthorizedException;
import com.alura.challenge_forumhub.repositories.TopicRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public ResponseEntity<TopicDetailsDto> saveTopic(TopicRegistrationDto topicRegistrationDto) {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setTitle(topicRegistrationDto.title());
        topicEntity.setMessage(topicRegistrationDto.message());
        topicEntity.setUser((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        topicEntity.setCreationDate(LocalDateTime.now());
        topicEntity.setStatus(TopicStatusEnum.NAO_RESPONDIDO);
        topicEntity.setCurse(topicRegistrationDto.curse());

        topicRepository.save(topicEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TopicDetailsDto(
                topicEntity.getId(),
                topicEntity.getTitle(),
                topicEntity.getMessage(),
                topicEntity.getUser().getName(),
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
                        t.getUser().getName(),
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
                        t.getUser().getName(),
                        t.getCreationDate(),
                        t.getStatus(),
                        t.getCurse(),
                        t.getAnswers()
                )));
    }

    public ResponseEntity<Void> deleteTopicById(Long id){
        UserEntity authenticatedUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<TopicEntity> topic = topicRepository.findById(id);

        if (topic.isEmpty() || topic.get().getStatus() == TopicStatusEnum.DELETADO) {
            throw new TopicNotFoundException("Tópico não encontrado ou já excluído.");
        }

        if (!Objects.equals(authenticatedUser.getId(), topic.get().getUser().getId())) {
            throw new UnauthorizedException("Não autorizado. O tópico só pode ser deletado pelo próprio autor.");
        }

        topic.get().setStatus(TopicStatusEnum.DELETADO);

        topicRepository.save(topic.get());

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<TopicDetailsUpdateDto> updateTopic (Long id, TopicRegistrationDto editTopic){
        UserEntity authenticatedUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<TopicEntity> topic = topicRepository.findById(id);

        if (topic.isEmpty() || topic.get().getStatus() == TopicStatusEnum.DELETADO) {
            throw new TopicNotFoundException("Tópico não encontrado ou já excluído.");
        }

        if (!Objects.equals(authenticatedUser.getId(), topic.get().getUser().getId())) {
            throw new UnauthorizedException("Não autorizado. O tópico só pode ser editado/deletado pelo próprio autor.");
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
                topic.get().getUser().getName(),
                topic.get().getCreationDate(),
                topic.get().getUpdateDate(),
                topic.get().getStatus(),
                topic.get().getCurse(),
                topic.get().getAnswers()
        ));
    }
}
