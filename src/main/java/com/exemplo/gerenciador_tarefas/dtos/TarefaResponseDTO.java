package com.exemplo.gerenciador_tarefas.dtos;

import com.exemplo.gerenciador_tarefas.entities.Tarefa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<TarefaResponseDTO> converter(List<Tarefa> tarefas) {
        return tarefas.stream()
                .map(TarefaResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Getters (pode incluir setters se necessário)
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
