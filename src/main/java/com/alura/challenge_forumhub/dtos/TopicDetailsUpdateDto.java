package com.alura.challenge_forumhub.dtos;

import com.alura.challenge_forumhub.enums.TopicStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

public record TopicDetailsUpdateDto(
        Long id,

        String title,

        String message,

        LocalDateTime creationDate,

        LocalDateTime updateDate,

        TopicStatusEnum status,

        String curse,

        List<String> answers
) {
}
