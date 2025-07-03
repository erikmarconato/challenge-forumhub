package com.alura.challenge_forumhub.dtos;

import jakarta.validation.constraints.NotBlank;

public record TopicRegistrationDto(
        @NotBlank
        String title,

        @NotBlank
        String message,

        @NotBlank
        String curse
) {
}
