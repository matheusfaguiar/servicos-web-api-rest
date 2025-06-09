-- Limpa a tabela (opcional)
DELETE FROM tarefas;

-- Insere as 3 tarefas iniciais
INSERT INTO tarefas (descricao, concluida, data_criacao, data_conclusao) VALUES 
('Tarefa de Debug 1 - Revisar código', false, CURRENT_TIMESTAMP, NULL),
('Tarefa de Debug 2 - Testar endpoints', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Tarefa de Debug 3 - Documentar API', false, CURRENT_TIMESTAMP, NULL);