package com.alura.challenge_forumhub.dtos;

import com.alura.challenge_forumhub.enums.TopicStatusEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record TopicDetailsDto(

        Long id,

        String title,

        String message,

        String user,

        LocalDateTime creationDate,

        TopicStatusEnum status,

        String curse,

        List<String> answers
) {
    public TopicDetailsDto(Long id, String title, String message, String user, LocalDateTime creationDate, TopicStatusEnum status, String curse, List<String> answers) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.user = user;
        this.creationDate = creationDate;
        this.status = status;
        this.curse = curse;
        this.answers = (answers != null) ? answers : new ArrayList<>();
    }
}
