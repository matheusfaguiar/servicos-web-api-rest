# Serviços Web e API REST - Gerenciador de Tarefas

API RESTful para gerenciamento de tarefas pessoais desenvolvida com Spring Boot.

## 📋 Visão Geral

Este projeto implementa um sistema completo de gerenciamento de tarefas com operações CRUD e suporte a criação em lote, utilizando DTOs para transferência de dados.

## 🚀 Tecnologias

- Java 24.0.1
- Spring Boot 3.5.0
- Spring Data JPA
- H2 Database (ambiente de desenvolvimento)
- Maven
- Git/GitHub

## 🏗️ Estrutura do Projeto
```
servicos-web-api-rest/
├── src/
│ ├── main/
│ │ ├── java/com/exemplo/gerenciador_tarefas/
│ │ │ ├── controllers/ # Controladores REST
│ │ │ ├── dtos/ # 
│ │ │ ├── entities/ # Entidades JPA
│ │ │ ├── repositories/ # Interfaces de repositório
│ │ │ ├── services/ # Lógica de negócio
│ │ │ └── GerenciadorTarefasApplication.java
│ │ └── resources/
│ │ │ ├── application.properties # Configurações
├── .gitignore
├── pom.xml # Dependências Maven
```


## 📡 Documentação da API

### Endpoints Principais

#### 1. Listar todas as tarefas: GET /api/tarefas


**Resposta de Sucesso (200 OK):**
```json
[
    {
        "id": 1,
        "descricao": "Fazer compras",
        "concluida": false,
        "dataCriacao": "2023-11-20T10:00:00",
        "dataConclusao": null
    }
]
```

#### 2. Obter uma tarefa específica: GET /api/tarefas/{id}

Parâmetros:

    id (path) - ID da tarefa

Respostas:

    200 OK - Tarefa encontrada

    404 Not Found - Tarefa não encontrada

#### 3. Criar nova tarefa: POST /api/tarefas

Corpo da Requisição:
```json

{
    "descricao": "Nova tarefa",
    "concluida": false
}
```

Respostas:

    201 Created - Tarefa criada com sucesso

    400 Bad Request - Dados inválidos

#### 4. Atualizar tarefa existente: PUT /api/tarefas/{id}

Parâmetros:

    id (path) - ID da tarefa

Corpo da Requisição:
```json

{
    "descricao": "Tarefa atualizada",
    "concluida": true
}
```

#### 5. Criar múltiplas tarefas: POST /api/tarefas/batch

Corpo da Requisição:
```json

[
    {
        "descricao": "Tarefa 1"
    },
    {
        "descricao": "Tarefa 2",
        "concluida": true
    }
]
```

#### 6. Deletar tarefa: DELETE /api/tarefas/{id}

Respostas:

    204 No Content - Tarefa removida com sucesso

    404 Not Found - Tarefa não encontrada

## 🛠️ Como Executar

Clone o repositório:

```bash

git clone https://github.com/matheusfaguiar/servicos-web-api-rest.git
```
Execute o projeto:

```bash

mvn spring-boot:run
```

Acesse a API:

http://localhost:8080/api/tarefas

## 📊 Banco de Dados

Em desenvolvimento, a aplicação utiliza um banco H2 em memória:

    URL: jdbc:h2:mem:taskdb

    Console: http://localhost:8080/h2-console

    Credenciais:

        User: sa

        Password: (vazio)
