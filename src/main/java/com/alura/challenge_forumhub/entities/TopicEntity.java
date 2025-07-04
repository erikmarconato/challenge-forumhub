package com.alura.challenge_forumhub.entities;

import com.alura.challenge_forumhub.enums.TopicStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "mensagem")
    private String message;

    @Column(name = "data_criacao")
    private LocalDateTime creationDate;

    @Column(name = "data_atualizacao")
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private TopicStatusEnum status;

    @Column(name = "curso")
    private String curse;

    @Column(name = "respostas")
    private List<String> answers = new ArrayList<>();

}


