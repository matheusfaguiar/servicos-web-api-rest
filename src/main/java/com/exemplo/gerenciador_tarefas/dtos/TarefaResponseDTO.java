package com.exemplo.gerenciador_tarefas.dto;

import com.exemplo.gerenciador_tarefas.entities.Tarefa;
import java.time.LocalDateTime;

public class TarefaResponseDTO {
    private Long id;
    private String descricao;
    private boolean concluida;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

    public TarefaResponseDTO(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.descricao = tarefa.getDescricao();
        this.concluida = tarefa.isConcluida();
        this.dataCriacao = tarefa.getDataCriacao();
        this.dataConclusao = tarefa.getDataConclusao();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }
}