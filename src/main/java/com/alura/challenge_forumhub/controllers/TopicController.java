package com.alura.challenge_forumhub.controllers;

import com.alura.challenge_forumhub.dtos.TopicDetailsDto;
import com.alura.challenge_forumhub.dtos.TopicDetailsUpdateDto;
import com.alura.challenge_forumhub.dtos.TopicRegistrationDto;
import com.alura.challenge_forumhub.services.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<List<TopicDetailsDto>> findAllTopics (){
        return topicService.findAllTopicsActive();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TopicDetailsDto>> findTopicById(@PathVariable Long id){
        return topicService.findTopicById(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteTopicById(@PathVariable Long id){
        return topicService.deleteTopicById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDetailsUpdateDto> updateTopic (@PathVariable Long id, @Valid @RequestBody TopicRegistrationDto editTopic){
        return topicService.updateTopic(id, editTopic);
    }
}
