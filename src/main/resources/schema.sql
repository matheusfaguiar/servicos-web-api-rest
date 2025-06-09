CREATE TABLE tarefas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    concluida BOOLEAN NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_conclusao TIMESTAMP
);