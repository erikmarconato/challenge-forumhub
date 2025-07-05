ALTER TABLE topicos
    ADD COLUMN usuario_id BIGINT NOT NULL;

ALTER TABLE topicos
    ADD CONSTRAINT fk_topicos_usuarios
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id);