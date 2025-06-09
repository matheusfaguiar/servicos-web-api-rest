package com.exemplo.gerenciador_tarefas.exceptions;

import com.exemplo.gerenciador_tarefas.dtos.ErroResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Validação @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            erros.put(error.getField(), error.getDefaultMessage());
        }
        String mensagem = "Erro de validação: " + erros.toString();
        ErroResponseDTO erroDTO = new ErroResponseDTO(mensagem, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(erroDTO, HttpStatus.BAD_REQUEST);
    }

    // Conversão de tipo de parâmetro (ex: string para long)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResponseDTO> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String mensagem = String.format("Parâmetro '%s' com valor inválido: '%s'. Tipo esperado: %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        ErroResponseDTO erroDTO = new ErroResponseDTO(mensagem, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(erroDTO, HttpStatus.BAD_REQUEST);
    }

    // Recurso não encontrado (404) - para URLs inválidas
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErroResponseDTO> handleNoHandlerFound(NoHandlerFoundException ex) {
        String mensagem = String.format("Recurso não encontrado: %s %s", ex.getHttpMethod(), ex.getRequestURL());
        ErroResponseDTO erroDTO = new ErroResponseDTO(mensagem, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(erroDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErroResponseDTO> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        String mensagem = "Método HTTP '" + ex.getMethod() + "' não é suportado para esta URL.";
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErroResponseDTO(mensagem, 405));
    }

    // Erro genérico - para outras exceções inesperadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponseDTO> handleException(Exception ex) {
        ErroResponseDTO erroDTO = new ErroResponseDTO("Ocorreu um erro inesperado: " + ex.getMessage(),
                                                      HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(erroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
