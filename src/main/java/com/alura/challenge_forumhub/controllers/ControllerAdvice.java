package com.alura.challenge_forumhub.controllers;

import com.alura.challenge_forumhub.exceptions.TopicNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<String> handleTopicNotFoundException (TopicNotFoundException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
