# 📝 Gerenciador de Tarefas - API RESTful

API RESTful para gerenciamento de tarefas pessoais desenvolvida com Spring Boot, com tratamento avançado de erros e documentação automatizada via Swagger.


## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot 3.2.6
- Spring Data JPA
- H2 Database (banco em memória para desenvolvimento)
- Maven
- SpringDoc OpenAPI 2.3.0 (Documentação)
- Spring Validation (Validação de dados)


## 🏗️ Estrutura do Projeto
```
gerenciador-tarefas/
├── src/
│   ├── main/
│   │   ├── java/com/exemplo/gerenciador_tarefas/
│   │   │   ├── controllers/       
│   │   │   ├── dtos/             
│   │   │   ├── entities/         
│   │   │   ├── exceptions/        # Tratamento de exceções
│   │   │   ├── repositories/      # Interfaces Spring Data JPA
│   │   │   ├── services/          # Lógica de negócio
│   │   │   └── GerenciadorTarefasApplication.java
│   │   └── resources/
│   │       ├── application.properties # Configurações
│   │       ├── data.sql           # Dados iniciais
│   │       └── schema.sql        # Schema do banco
├── target/
├── .gitignore
├── pom.xml
├── HELP.md
└── README.md
```

## 📡 Documentação da API (Swagger UI)

Acesse a documentação interativa em:
http://localhost:8080/swagger-ui.html


## 🌐 Endpoints da API (v1)

### TarefaController
|Método	  |Endpoint	   |Descrição |
|---------|------------|----------|
GET |	/api/v1/tarefas	| Lista todas as tarefas
GET	|/api/v1/tarefas/{id}	| Obtém uma tarefa específica
POST |	/api/v1/tarefas	| Cria uma nova tarefa
POST |	/api/v1/tarefas/batch|	Cria múltiplas tarefas em lote
PUT	| /api/v1/tarefas/{id}	| Atualiza uma tarefa existente
DELETE	| /api/v1/tarefas/{id}	| Remove uma tarefa


## 🛠️ Como Executar
Pré-requisitos:
- Java 17 instalado
- Maven instalado
- Clone e execute:
```bash

    git clone https://github.com/matheusfaguiar/servicos-web-api-rest.git
    cd servicos-web-api-rest
    mvn spring-boot:run
```
Acesse a aplicação:
- API: http://localhost:8080/api/v1/tarefas
- Swagger UI: http://localhost:8080/swagger-ui.html
 H2 Console: http://localhost:8080/h2-console

## 📊 Configuração do Banco de Dados (H2)
- URL JDBC: jdbc:h2:mem:taskdb
- Usuário: sa
- Senha: (vazia)
- Console H2: Habilitado em http://localhost:8080/h2-console


## 🛡️ Tratamento de Erros

A API retorna respostas padronizadas para erros:
```json
{
  "mensagem": "Tarefa com id 999 não encontrada",
  "codigo": 404
}
```


## 📌 Exemplo de Requisições

Criar tarefa:
```bash

curl -X POST "http://localhost:8080/api/v1/tarefas" \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Estudar Spring Boot",
    "concluida": false
  }'
```
Listar tarefas:

```bash
curl "http://localhost:8080/api/v1/tarefas"
```

Atualizar tarefa:
```bash
curl -X PUT "http://localhost:8080/api/v1/tarefas/1" \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Estudar Spring Boot atualizado",
    "concluida": true
  }'
```


## 🔧 Dependências Principais (pom.xml)
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- H2 Database
- SpringDoc OpenAPI (Swagger)
- Spring Boot Starter Test (para testes)
  

## ⚙️ Configurações Principais (application.properties)
- Habilitação do H2 Console
- Configurações JPA/Hibernate
- Inicialização automática do banco de dados (data.sql e schema.sql)
- Configurações do Swagger/OpenAPI
