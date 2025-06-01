package com.exemplo.gerenciador_tarefas.repositories;

import com.exemplo.gerenciador_tarefas.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByConcluida(boolean concluida);
    List<Tarefa> findByDescricaoContainingIgnoreCase(String descricao);
}