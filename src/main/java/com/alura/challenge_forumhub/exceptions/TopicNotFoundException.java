package com.alura.challenge_forumhub.exceptions;

public class TopicNotFoundException extends RuntimeException{
    public TopicNotFoundException(String message) {
        super(message);
    }
}
