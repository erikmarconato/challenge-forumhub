package com.alura.challenge_forumhub.services;

import com.alura.challenge_forumhub.dtos.TopicDetailsDto;
import com.alura.challenge_forumhub.dtos.TopicRegistrationDto;
import com.alura.challenge_forumhub.entities.TopicEntity;
import com.alura.challenge_forumhub.enums.TopicStatusEnum;
import com.alura.challenge_forumhub.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
                topicEntity.getCurse()
        ));
    }
}
