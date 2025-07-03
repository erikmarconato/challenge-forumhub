package com.alura.challenge_forumhub.controllers;

import com.alura.challenge_forumhub.dtos.TopicDetailsDto;
import com.alura.challenge_forumhub.dtos.TopicRegistrationDto;
import com.alura.challenge_forumhub.services.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDetailsDto> saveTopic(@RequestBody @Valid TopicRegistrationDto topicRegistrationDto){
        return topicService.saveTopic(topicRegistrationDto);
    }
}
