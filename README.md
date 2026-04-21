🎬 MovieFlix API

Este é um projeto de Backend (Lado do servidor / Lógica de acesso a dados) desenvolvido em Java com Spring Boot. O objetivo principal é fornecer uma API (Application Programming Interface - Interface de Programação de Aplicações) robusta para o controle e cadastro de um catálogo de filmes, permitindo gerenciar filmes, categorias e plataformas de Streaming (Transmissão contínua de mídia).

📚 Créditos de Estudo: Este projeto foi construído com base no conhecimento e práticas ensinadas no curso Java10x (Módulo de Documentação com Swagger: Acessar aula). O repositório original que serviu de inspiração para a base do projeto pode ser encontrado aqui (miniature-fortnight).

🔗 Meu Repositório Oficial: java10x-movieflix
🛠️ Tecnologias, Ferramentas e Bibliotecas Utilizadas

Abaixo estão as tecnologias exatas configuradas no meu projeto (visíveis no arquivo pom.xml e na estrutura do código):

    Java 21: Versão atualizada da linguagem de programação principal.

    Spring Boot (Web, JPA, Security, Validation): Framework (Estrutura de trabalho base) que facilita a configuração, segurança, validação de dados e criação dos Endpoints (Pontos de acesso da API).

    JWT (JSON Web Token da Auth0 - v4.4.0): Tecnologia utilizada para gerar e validar tokens (fichas/chaves de acesso) criptografados, garantindo um Login seguro.

    PostgreSQL: Banco de dados relacional escolhido para armazenar as informações do sistema.

    Flyway: Ferramenta de Migrations (Migrações), responsável por versionar e automatizar a criação e alteração das tabelas no banco de dados.

    Lombok: Biblioteca que reduz a repetição de código em Java (gerando getters, setters e construtores automaticamente em tempo de compilação).

    Springdoc OpenAPI / Swagger (v3.0.2): Biblioteca responsável por ler as anotações no código (como @Tag, @Operation) e gerar uma página visual e interativa para testar a API.

🏗️ Arquitetura e Modelagem de Dados

O projeto foi modelado para refletir a realidade de catálogos modernos de filmes. De acordo com o diagrama de classes do sistema, temos as seguintes relações principais:

    Movie (Filme) ↔ Category (Categoria): Relação N para N (Muitos para Muitos). Um filme pode pertencer a várias categorias (ex: Ação e Comédia), e uma categoria possui vários filmes.

    Movie (Filme) ↔ Streaming (Plataforma): Relação N para N (Muitos para Muitos). Um filme pode estar disponível em diversas plataformas (ex: Netflix e Prime Video), e uma plataforma possui diversos filmes.

⚙️ Funcionalidades

A API foi dividida em rotas (Controllers) bem definidas, possuindo proteção por Token nas operações de cadastro e consulta (exceto na criação de usuários e login):

    Categorias (Category): Listagem, busca por ID, criação e remoção.

    Serviços de Streaming (Streaming): Listagem, busca por ID, criação e remoção.

    Filmes (Movie): Gerenciamento completo (Criar, Listar Todos, Buscar por ID, Buscar por Categoria, Atualizar e Deletar). Completamente documentado com anotações OpenAPI.

    Usuários e Autenticação (Auth): Registro de novos usuários e Login para geração do Token Bearer (Portador).

🚀 Como Executar o Projeto Localmente

Para rodar este projeto na sua máquina para estudos, siga os passos abaixo:

1. Clone o repositório:
   Bash

git clone https://github.com/cicero-gledson/java10x-movieflix.git

2. Configure o Banco de Dados (Atenção à porta):
   Certifique-se de ter o PostgreSQL rodando localmente. Conforme configurado no meu arquivo application.properties, o banco de dados deve estar na porta 5431 (diferente do padrão 5432) e se chamar movieflix:
   Properties

spring.datasource.url=jdbc:postgresql://localhost:5431/movieflix
spring.datasource.username=postgres
spring.datasource.password=postgres

Dica para memorização: Se for rodar um contêiner Docker para o banco, lembre-se de mapear a porta 5431:5432.

3. Inicie a Aplicação:
   Abra o projeto na sua IDE favorita (como o IntelliJ IDEA) e execute a classe MovieflixApplication.java. O Flyway executará automaticamente as tabelas (ddl-auto=validate garante que a estrutura via migrações esteja correta).
   📖 Documentação Interativa (Swagger)

A API possui uma documentação visual onde você pode testar todas as requisições sem precisar de um programa externo (como o Postman). Como eu customizei o caminho base no application.properties, você pode acessar a documentação nos seguintes endereços com a aplicação rodando:

    📄 Visualização Interativa (Swagger UI): http://localhost:8080/swagger/index.html

    ⚙️ Estrutura JSON (API Docs): http://localhost:8080/api/api-docs

    Como testar rotas protegidas: > 1. Crie um usuário na rota de Registro.
    2. Faça Login para receber um Token longo.
    3. Clique no botão "Authorize" no topo da tela do Swagger e cole o seu Token. Agora você pode testar as rotas de Filmes, Categorias e Streamings!