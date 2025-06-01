package com.exemplo.gerenciador_tarefas.dtos;

import jakarta.validation.constraints.NotBlank;

public class TarefaRequestDTO {
    
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}