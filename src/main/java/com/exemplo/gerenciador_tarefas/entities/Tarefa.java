package com.exemplo.gerenciador_tarefas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;
    
    private boolean concluida = false;
    
    @NotNull
    private LocalDateTime dataCriacao;
    
    private LocalDateTime dataConclusao;
    
    // Construtores
    public Tarefa() {
        this.dataCriacao = LocalDateTime.now();
    }
    
    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.dataCriacao = LocalDateTime.now();
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
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
        if (concluida) {
            this.dataConclusao = LocalDateTime.now();
        } else {
            this.dataConclusao = null;
        }
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

     public void setDataCriacao(LocalDateTime now) {
        this.dataCriacao = now;
    }
    public void setDataConclusao(LocalDateTime now) {
        this.dataConclusao = now;
    }
}