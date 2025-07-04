package com.alura.challenge_forumhub.dtos;

import com.alura.challenge_forumhub.enums.TopicStatusEnum;

import java.time.LocalDateTime;

public record TopicDetailsUpdateDto(
        Long id,

        String title,

        String message,

        LocalDateTime creationDate,

        LocalDateTime updateDate,

        TopicStatusEnum status,

        String curse
) {
}
