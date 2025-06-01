package com.exemplo.gerenciador_tarefas.services;

import com.exemplo.gerenciador_tarefas.dtos.TarefaRequestDTO;
import com.exemplo.gerenciador_tarefas.dtos.TarefaResponseDTO;
import com.exemplo.gerenciador_tarefas.entities.Tarefa;
import com.exemplo.gerenciador_tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    /**
     * Lista todas as tarefas cadastradas
     * @return Lista de tarefas
     */
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    /**
     * Busca uma tarefa pelo ID
     * @param id ID da tarefa
     * @return Tarefa encontrada (ou Optional vazio se não existir)
     */
    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    /**
     * Cria uma nova tarefa
     * @param tarefa Dados da tarefa a ser criada
     * @return Tarefa salva
     */
    @Transactional
    public Tarefa criarTarefa(Tarefa tarefa) {
        tarefa.setDataCriacao(LocalDateTime.now());
        return tarefaRepository.save(tarefa);
    }

    /**
     * Atualiza uma tarefa existente
     * @param id ID da tarefa a ser atualizada
     * @param tarefaAtualizada Dados atualizados da tarefa
     * @return Tarefa atualizada
     * @throws RuntimeException Se a tarefa não for encontrada
     */
    @Transactional
    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setDescricao(tarefaAtualizada.getDescricao());
                    tarefa.setConcluida(tarefaAtualizada.isConcluida());
                    return tarefaRepository.save(tarefa);
                })
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));
    }

    /**
     * Exclui uma tarefa pelo ID
     * @param id ID da tarefa a ser excluída
     */
    @Transactional
    public void excluirTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    /**
     * Marca uma tarefa como concluída
     * @param id ID da tarefa
     * @return Tarefa atualizada
     * @throws RuntimeException Se a tarefa não for encontrada
     */
    @Transactional
    public Tarefa marcarComoConcluida(Long id) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setConcluida(true);
                    return tarefaRepository.save(tarefa);
                })
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));
    }

    /**
     * Marca uma tarefa como pendente
     * @param id ID da tarefa
     * @return Tarefa atualizada
     * @throws RuntimeException Se a tarefa não for encontrada
     */
    @Transactional
    public Tarefa marcarComoPendente(Long id) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setConcluida(false);
                    return tarefaRepository.save(tarefa);
                })
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));
    }


    // Métodos atualizados para usar DTOs
    public TarefaResponseDTO criarTarefa(TarefaRequestDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(tarefaDTO.getDescricao());
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return new TarefaResponseDTO(tarefaSalva);
    }

    public TarefaResponseDTO atualizarTarefa(Long id, TarefaRequestDTO tarefaDTO) {
        return tarefaRepository.findById(id)
            .map(tarefa -> {
                tarefa.setDescricao(tarefaDTO.getDescricao());
                Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
                return new TarefaResponseDTO(tarefaAtualizada);
            })
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));
    }
}