package com.alura.challenge_forumhub.controllers;

import com.alura.challenge_forumhub.dtos.AuthenticationDto;
import com.alura.challenge_forumhub.dtos.LoginResponseDto;
import com.alura.challenge_forumhub.dtos.RegisterUserDto;
import com.alura.challenge_forumhub.services.AuthenticationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<LoginResponseDto> login (@RequestBody @Valid AuthenticationDto authenticationDto){
        return authenticationService.login(authenticationDto);
    }

    @PostMapping("/registro")
    @Transactional
    public ResponseEntity<Void> registerUser (@RequestBody @Valid RegisterUserDto registerUserDto){
        return authenticationService.registerUser(registerUserDto);
    }
}
