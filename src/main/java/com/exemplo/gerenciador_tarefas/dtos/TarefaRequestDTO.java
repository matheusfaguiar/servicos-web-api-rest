package com.exemplo.gerenciador_tarefas.dtos;

import jakarta.validation.constraints.NotBlank;

public class TarefaRequestDTO {

    @NotBlank
    private String descricao;

    private boolean concluida;

    // Getters e setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
