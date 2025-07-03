package com.alura.challenge_forumhub.dtos;

import com.alura.challenge_forumhub.enums.TopicStatusEnum;

import java.time.LocalDateTime;

public record TopicDetailsDto(

        Long id,

        String title,

        String message,

        LocalDateTime creationDate,

        TopicStatusEnum status,

        String curse
) {
}
