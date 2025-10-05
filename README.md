
-----

### `README.md`

````markdown
# To-Do List API | Spring Boot

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Build-blue.svg)](https://www.docker.com/)
[![Render](https://img.shields.io/badge/Deploy-Render-cyan.svg)](https://render.com/)
[![Status](https://img.shields.io/badge/Status-Online-brightgreen.svg)](https://todolist-springboot-1-mh2v.onrender.com)

API RESTful para gerenciamento de tarefas, desenvolvida com foco em boas práticas de arquitetura de software, Clean Code e padrões de mercado do ecossistema Spring.

**A API está no ar e pode ser testada em:** `(https://todolist-springboot-1-mh2v.onrender.com)`

---

##Features

-   **Autenticação de Usuários:** Sistema de criação e autenticação de usuários com senha criptografada (BCrypt) via Basic Auth.
-   **Gerenciamento de Tarefas (CRUD):**
    -   Criação de novas tarefas vinculadas ao usuário autenticado.
    -   Listagem de todas as tarefas do usuário.
    -   Atualização parcial de tarefas existentes.
-   **Validação de Dados:** Regras de negócio implementadas para garantir a integridade dos dados, como:
    -   Títulos de tarefas com no máximo 50 caracteres.
    -   Datas de início e término devem ser futuras.
    -   A data de início deve ser anterior à data de término.
-   **Tratamento de Exceções:** Handler global para exceções, retornando mensagens de erro claras e os códigos de status HTTP apropriados.

---

##Arquitetura e Padrões

Este projeto foi estruturado para ser robusto, escalável e de fácil manutenção, aplicando os seguintes conceitos:

-   **Camada de Serviço (Service Layer):** Separação clara das responsabilidades, com a lógica de negócio isolada dos controllers.
-   **DTO (Data Transfer Object):** Utilização de DTOs para os contratos de entrada (Request) e saída (Response) da API, garantindo segurança e desacoplamento entre a API e o modelo de dados.
-   **Injeção de Dependências:** Uso do padrão de injeção de dependências do Spring para gerenciar os componentes da aplicação.
-   **Filtro de Autenticação:** Um `OncePerRequestFilter` customizado para proteger os endpoints de tarefas.

---

##Tecnologias

| Tecnologia | Versão/Tipo | Descrição |
| :--- | :--- | :--- |
| **Java** | 17 | Linguagem principal da aplicação. |
| **Spring Boot** | 3.x | Framework principal para construção da API. |
| **Spring Data JPA** | - | Para persistência de dados e comunicação com o banco. |
| **H2 Database** | Em Memória | Banco de dados para ambiente de desenvolvimento e deploy. |
| **Maven** | - | Gerenciador de dependências e build do projeto. |
| **Lombok** | - | Para redução de código boilerplate em modelos e DTOs. |
| **Docker** | - | Para criar uma imagem conteinerizada da aplicação. |
| **Render** | Nuvem | Plataforma de PaaS para o deploy e hospedagem da API. |

---

##Endpoints da API

###Autenticação
A autenticação é feita via **Basic Auth**. Utilize o `username` e `password` do usuário criado nos endpoints que requerem autenticação.

###Usuários
| Verbo | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/users` | Cria um novo usuário. |

**Exemplo de corpo (request) para `POST /users`:**
```json
{
    "username": "meu_usuario",
    "name": "Meu Nome",
    "password": "minha_senha_123"
}
````

###Tarefas

| Verbo | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/tasks` | Cria uma nova tarefa (requer autenticação). |
| `GET` | `/tasks` | Lista todas as tarefas do usuário (requer autenticação). |
| `PUT` | `/tasks/{id}` | Atualiza uma tarefa existente (requer autenticação). |

**Exemplo de corpo (request) para `POST /tasks`:**

```json
{
    "title": "Finalizar o README do projeto",
    "description": "Usar o template profissional com badges e tabelas.",
    "startAt": "2025-10-06T10:00:00",
    "endAt": "2025-10-06T12:00:00",
    "priority": "Alta"
}
```



```
```




