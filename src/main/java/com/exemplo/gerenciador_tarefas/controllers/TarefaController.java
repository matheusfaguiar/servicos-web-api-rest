package com.exemplo.gerenciador_tarefas.controllers;

import com.exemplo.gerenciador_tarefas.entities.Tarefa;
import com.exemplo.gerenciador_tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaRepository tarefaRepository;
    
    // GET - Listar todas as tarefas
    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTodas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return ResponseEntity.ok(tarefas);
    }

    // GET - Obter tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        
        return tarefa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // POST - Criar nova tarefa
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        try {
            Tarefa novaTarefa = tarefaRepository.save(tarefa);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Tarefa>> criarTarefasEmLote(@RequestBody List<Tarefa> tarefas) {
        try {
            List<Tarefa> tarefasSalvas = tarefaRepository.saveAll(tarefas);
            return ResponseEntity.status(HttpStatus.CREATED).body(tarefasSalvas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // PUT - Atualizar tarefa existente
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setDescricao(tarefaAtualizada.getDescricao());
                    tarefa.setConcluida(tarefaAtualizada.isConcluida());
                    Tarefa tarefaAtualizadaSalva = tarefaRepository.save(tarefa);
                    return ResponseEntity.ok(tarefaAtualizadaSalva);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // DELETE - Remover tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable Long id) {
        try {
            tarefaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}