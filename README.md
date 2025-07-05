# 🚀 Challenge ForumHub - ONE G8 BR Alura + Oracle

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.3-green?style=for-the-badge&logo=spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-Auth0-yellow?style=for-the-badge&logo=json-web-tokens)
![Flyway](https://img.shields.io/badge/Flyway-Migration-purple?style=for-the-badge&logo=flyway)

**Uma API REST completa para gerenciamento de fórum de discussão desenvolvida como parte do Challenge do ONE G8 BR Alura + Oracle**

[![Alura](https://img.shields.io/badge/Alura-ONE_G8-orange?style=for-the-badge)](https://www.alura.com.br/)
[![Oracle](https://img.shields.io/badge/Oracle-Cloud-red?style=for-the-badge&logo=oracle)](https://www.oracle.com/br/cloud/)

</div>

---

## 📋 Índice

- [🎯 Sobre o Projeto](#-sobre-o-projeto)
- [✨ Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [🏗️ Arquitetura](#️-arquitetura)
- [🚀 Como Executar](#-como-executar)
- [📚 Documentação da API](#-documentação-da-api)
- [🔐 Autenticação e Segurança](#-autenticação-e-segurança)

---

## 🎯 Sobre o Projeto

O **Challenge ForumHub** é uma API REST robusta desenvolvida em **Spring Boot** que implementa um sistema completo de fórum de discussão. Este projeto foi desenvolvido como parte do **Challenge do ONE G8 BR Alura + Oracle**, demonstrando as melhores práticas de desenvolvimento backend com Java.

### 🎖️ Objetivos do Challenge

- Implementar uma API REST completa com Spring Boot
- Demonstrar conhecimento em autenticação JWT
- Aplicar boas práticas de segurança
- Utilizar migração de banco de dados com Flyway
- Implementar validações e tratamento de exceções
- Seguir princípios SOLID e Clean Code

---

## ✨ Funcionalidades

### 🔐 Autenticação e Autorização
- **Registro de usuários** com validação de dados
- **Login com JWT** e criptografia de senhas
- **Autorização baseada em roles** (USER)
- **Filtro de segurança** para proteção das rotas

### 📝 Gestão de Tópicos
- ✅ **Criar tópicos** com título, mensagem e curso
- 📋 **Listar todos os tópicos** ativos
- 🔍 **Buscar tópico por ID**
- ✏️ **Atualizar tópicos** (apenas pelo autor)
- 🗑️ **Deletar tópicos** (soft delete, apenas pelo autor)

### 🏷️ Sistema de Status
- **NÃO_RESPONDIDO**: Tópico aguardando resposta
- **RESPONDIDO**: Tópico com respostas
- **DELETADO**: Tópico removido (soft delete)

### 🛡️ Segurança
- **BCrypt** para criptografia de senhas
- **JWT** para autenticação stateless
- **Validação de dados** com Bean Validation
- **Tratamento de exceções** personalizado
- **Autorização por usuário** (cada usuário só pode editar/deletar seus próprios tópicos)

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem principal
- **Spring Boot 3.5.3** - Framework principal
- **Spring Security** - Segurança e autenticação
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validação de dados

### Banco de Dados
- **PostgreSQL** - Banco de dados principal
- **Flyway** - Migração de banco de dados
- **Hibernate** - ORM

### Autenticação
- **JWT (Auth0)** - Tokens de autenticação
- **BCrypt** - Criptografia de senhas

### Ferramentas
- **Maven** - Gerenciamento de dependências
- **Lombok** - Redução de boilerplate
- **Git** - Controle de versão

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

```
📁 challenge-forumhub/
├── 🎯 Controllers (REST APIs)
├── 🔧 Services (Lógica de Negócio)
├── 📊 Repositories (Acesso a Dados)
├── 🗃️ Entities (Modelos de Dados)
├── 📦 DTOs (Transferência de Dados)
├── 🔐 Security (Autenticação/Autorização)
├── ⚠️ Exceptions (Tratamento de Erros)
└── 🗄️ Database (Migrações)
```

### Padrões Utilizados
- **MVC (Model-View-Controller)**
- **Repository Pattern**
- **DTO Pattern**
- **Service Layer Pattern**
- **Filter Pattern** (Security)

---

## 🚀 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### 1. Clone o Repositório
```bash
git clone https://github.com/seu-usuario/challenge-forumhub.git
cd challenge-forumhub
```

### 2. Configure o Banco de Dados
Crie um banco PostgreSQL e configure as variáveis de ambiente:

```bash
# .env ou variáveis de ambiente
DB_URL=jdbc:postgresql://localhost:5432/forumhub
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
JWT_SECRET=sua_chave_secreta_jwt_super_segura
```

### 3. Execute o Projeto
```bash
# Compilar e executar
mvn spring-boot:run

# Ou compilar primeiro
mvn clean install
java -jar target/challenge-forumhub-0.0.1-SNAPSHOT.jar
```

### 4. Acesse a API
A aplicação estará disponível em: `http://localhost:8080`

---

## 📚 Documentação da API

### 🔐 Endpoints de Autenticação

#### Registrar Usuário
```http
POST /auth/registro
Content-Type: application/json

{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "senha123"
}
```

#### Login
```http
POST /auth/login
Content-Type: application/json

{
  "email": "joao@email.com",
  "password": "senha123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 📝 Endpoints de Tópicos

#### Criar Tópico
```http
POST /topicos
Authorization: Bearer {token}
Content-Type: application/json

{
  "title": "Dúvida sobre Spring Boot",
  "message": "Como configurar o Spring Security?",
  "curse": "Spring Boot"
}
```

#### Listar Tópicos
```http
GET /topicos
```

#### Buscar Tópico por ID
```http
GET /topicos/{id}
```

#### Atualizar Tópico
```http
PUT /topicos/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "title": "Dúvida sobre Spring Boot - Atualizado",
  "message": "Como configurar o Spring Security? (Atualizado)",
  "curse": "Spring Boot"
}
```

#### Deletar Tópico
```http
DELETE /topicos/{id}
Authorization: Bearer {token}
```

---

## 🔐 Autenticação e Segurança

### JWT Token
- **Algoritmo**: HMAC256
- **Expiração**: 2 horas
- **Claims**: email, role
- **Issuer**: auth-api

### Rotas Públicas
- `GET /topicos` - Listar tópicos
- `GET /topicos/{id}` - Buscar tópico
- `POST /auth/registro` - Registrar usuário
- `POST /auth/login` - Login

### Rotas Protegidas
- `POST /topicos` - Criar tópico
- `PUT /topicos/{id}` - Atualizar tópico
- `DELETE /topicos/{id}` - Deletar tópico

### Autorização
- Usuários só podem editar/deletar seus próprios tópicos
- Validação de propriedade no nível de serviço

---
