package com.alura.challenge_forumhub.services;

import com.alura.challenge_forumhub.dtos.AuthenticationDto;
import com.alura.challenge_forumhub.dtos.LoginResponseDto;
import com.alura.challenge_forumhub.dtos.RegisterUserDto;
import com.alura.challenge_forumhub.entities.UserEntity;
import com.alura.challenge_forumhub.infra.security.TokenService;
import com.alura.challenge_forumhub.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public ResponseEntity<LoginResponseDto> login (AuthenticationDto authenticationDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.email(), authenticationDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    public ResponseEntity<Void> registerUser (RegisterUserDto registerUserDto){
        if (userRepository.findByEmail(registerUserDto.email()).isPresent()){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerUserDto.password());
        UserEntity user = new UserEntity();
        user.setName(registerUserDto.name());
        user.setEmail(registerUserDto.email());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
