package com.exemplo.gerenciador_tarefas.controllers;

import com.exemplo.gerenciador_tarefas.dtos.ErroResponseDTO;
import com.exemplo.gerenciador_tarefas.dtos.TarefaRequestDTO;
import com.exemplo.gerenciador_tarefas.dtos.TarefaResponseDTO;
import com.exemplo.gerenciador_tarefas.entities.Tarefa;
import com.exemplo.gerenciador_tarefas.services.TarefaService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tag(name = "Tarefas", description = "Operações relacionadas ao agendamento de tarefas")
@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefaController {

    private static final Logger logger = LoggerFactory.getLogger(TarefaController.class);

    @Autowired
    private TarefaService tarefaService;


    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listarTodas() {
        List<Tarefa> tarefas = tarefaService.listarTodas();
        return ResponseEntity.ok(TarefaResponseDTO.converter(tarefas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarPorId(id);
        if (tarefa.isPresent()) {
            return ResponseEntity.ok(new TarefaResponseDTO(tarefa.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroResponseDTO("Tarefa com id " + id + " não encontrada", 404));
        }
    }



    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criarTarefa(@RequestBody @Valid TarefaRequestDTO dto) {
        TarefaResponseDTO tarefaCriada = tarefaService.criarTarefa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCriada);
    }

    @PostMapping("/batch")
    public ResponseEntity<?> criarTarefasEmLote(@Valid @RequestBody List<TarefaRequestDTO> dtos) {
        try {
            List<TarefaResponseDTO> tarefasCriadas = tarefaService.criarTarefasEmLote(dtos);
            return ResponseEntity.status(HttpStatus.CREATED).body(tarefasCriadas);
        } catch (Exception e) {
            logger.error("Erro ao criar tarefas em lote", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErroResponseDTO("Erro ao criar tarefas em lote: " + e.getMessage(), 400));
        }
    }



    @PutMapping
    public ResponseEntity<String> handleInvalidPut() {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("Erro: O método PUT requer um ID. Use a URL no formato: /tarefas/{id}.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefaRequestDTO dto) {
        try {
            TarefaResponseDTO tarefaAtualizada = tarefaService.atualizarTarefa(id, dto);
            return ResponseEntity.ok(tarefaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroResponseDTO(e.getMessage(), 404));
        }
    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> removerTarefa(@PathVariable Long id) {
    try {
        tarefaService.excluirTarefa(id);
        return ResponseEntity.ok(new ErroResponseDTO("Tarefa removida com sucesso.", 200));
    } catch (RuntimeException e) {
        String mensagemErro = e.getMessage() != null && !e.getMessage().isEmpty() 
                              ? e.getMessage() 
                              : "Tarefa não encontrada para o ID: " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroResponseDTO(mensagemErro, 404));
    }
}


}

