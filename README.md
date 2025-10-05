# To-Do List API | Spring Boot

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Build-blue.svg)](https://www.docker.com/)
[![Render](https://img.shields.io/badge/Deploy-Render-cyan.svg)](https://render.com/)
[![Status](https://img.shields.io/badge/Status-Online-brightgreen.svg)](https://todolist-springboot-1-mh2v.onrender.com)

API RESTful para gerenciamento de tarefas, desenvolvida com foco em boas práticas de arquitetura de software, Clean Code e padrões de mercado do ecossistema Spring.

**🚀 A API está no ar e pode ser testada em:** `https://todolist-springboot-1-mh2v.onrender.com`

---

## Features

- **Autenticação de Usuários:** Sistema de criação e autenticação de usuários com senha criptografada (BCrypt) via Basic Auth.
- **Gerenciamento de Tarefas (CRUD):**
  - Criação de novas tarefas vinculadas ao usuário autenticado.
  - Listagem de todas as tarefas do usuário.
  - Atualização parcial de tarefas existentes.
- **Validação de Dados:** Regras de negócio implementadas para garantir a integridade dos dados, como:
  - Títulos de tarefas com no máximo 50 caracteres.
  - Datas de início e término devem ser futuras.
  - A data de início deve ser anterior à data de término.
- **Tratamento de Exceções:** Handler global para exceções, retornando mensagens de erro claras e os códigos de status HTTP apropriados.

---

## Arquitetura e Padrões

Este projeto foi estruturado para ser robusto, escalável e de fácil manutenção, aplicando os seguintes conceitos:

- **Camada de Serviço (Service Layer):** Separação clara das responsabilidades, com a lógica de negócio isolada dos controllers.
- **DTO (Data Transfer Object):** Utilização de DTOs para os contratos de entrada (Request) e saída (Response) da API, garantindo segurança e desacoplamento entre a API e o modelo de dados.
- **Injeção de Dependências:** Uso do padrão de injeção de dependências do Spring para gerenciar os componentes da aplicação.
- **Filtro de Autenticação:** Um `OncePerRequestFilter` customizado para proteger os endpoints de tarefas.

---

## Tecnologias

| Tecnologia      | Versão/Tipo | Descrição                                             |
| :-------------- | :---------- | :---------------------------------------------------- |
| **Java** | 17          | Linguagem principal da aplicação.                     |
| **Spring Boot** |
