package com.exemplo.gerenciador_tarefas.dtos;

import java.time.LocalDateTime;

public class ErroResponseDTO {

    private String mensagem;
    private int status;
    private LocalDateTime timestamp;

    public ErroResponseDTO() {
    }

    public ErroResponseDTO(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
