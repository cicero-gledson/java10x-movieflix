# 🎬 MovieFlix - API de Catálogo de Filmes

O **MovieFlix** é uma API REST desenvolvida para gerir um catálogo de filmes, categorias e serviços de streaming. O projeto foi construído focado em boas práticas de backend, segurança e escalabilidade.

---

## 🚀 Tecnologias e Bibliotecas

* **Java 21** & **Spring Boot 4.0.5**
* **Spring Security** & **JWT** (via `com.auth0:java-jwt`)
* **Spring Data JPA** (Hibernate)
* **PostgreSQL** (Base de dados relacional)
* **Flyway** (Gestão de migrations)
* **Lombok** (Produtividade)
* **Bean Validation** (Validação de dados)
* **OpenAPI 3.0** (Swagger para documentação)

---

## 🛠️ Arquitetura e Funcionalidades

### Recursos Principais
* **Categorias:** Gestão de gêneros cinematográficos.
* **Streaming:** Registro de plataformas de distribuição.
* **Filmes:** Cadastro detalhado com relacionamentos N:N (Muitos para Muitos) entre filmes, categorias e streamings.
* **Usuários:** Sistema de registro e autenticação.

### Segurança
* **Stateless:** Autenticação baseada em tokens JWT.
* **Criptografia:** Senhas armazenadas de forma segura utilizando BCrypt.
* **Filtros Personalizados:** Validação de token em cada requisição via `OncePerRequestFilter`.

---

## ⚙️ Configuração e Instalação

### 1. Pré-requisitos
* JDK 21 ou superior.
* Maven 3.x.
* Instância do PostgreSQL ativa.

### 2. Configuração da Base de Dados
No arquivo `src/main/resources/application.yaml` (ou `.properties`), configure a conexão:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/movieflix
    username: seu_usuario
    password: sua_senha
  jpa:
    hibernate:
      ddl-auto: validate
  flyway:
    enabled: true