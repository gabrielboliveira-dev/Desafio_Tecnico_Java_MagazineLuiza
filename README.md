# Desafio Técnico: API de Wishlist (Magazine Luiza)

## Contexto

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot, destinada ao gerenciamento da "Wishlist" (lista de desejos) de clientes. A API permite que um cliente adicione, remova e consulte produtos em sua lista de desejos.

Este desafio foca em demonstrar boas práticas de design de API, modelagem de dados de relacionamento e aplicação dos princípios SOLID em um contexto de microsserviços.

## 🚀 Requisitos Funcionais

A API deve permitir as seguintes operações:

1.  **Adicionar** um produto à lista de desejos de um cliente.
2.  **Remover** um produto da lista de desejos de um cliente.
3.  **Consultar** todos os produtos da lista de desejos de um cliente.
4.  **Validação:** Garantir que o cliente e o produto existem (simularemos isso) e tratar regras de negócio (ex: não adicionar um produto duplicado).

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3** (Spring Web, Spring Data JPA)
* **H2 Database** (Banco de dados em memória para desenvolvimento/testes)
* **Maven** (Gerenciador de dependências)
* **Lombok** (Para redução de boilerplate)

## 🎯 Objetivos de Aprendizado (Clean Code & SOLID)

* **Modelagem de Entidades:** Focar em como modelar um *relacionamento* (a Wishlist) que depende de outras entidades (Cliente e Produto).
* **Princípio da Responsabilidade Única (SRP):** Manter Controllers, Services e Repositories com responsabilidades claras.
* **Injeção de Dependência (DIP):** Usar a inversão de dependência do Spring para desacoplar componentes.
* **Design de API RESTful:** Criar endpoints intuitivos e seguir os padrões HTTP.
* **Tratamento de Exceções:** Implementar um `ControllerAdvice` para lidar com erros de forma centralizada (ex: Cliente não encontrado, Produto não encontrado).

## Endpoints da API (Contrato)

| Método | URI | Descrição |
| :--- | :--- | :--- |
| `POST` | `/wishlist/{clientId}/products` | Adiciona um produto (via RequestBody) à lista do cliente. |
| `DELETE` | `/wishlist/{clientId}/products/{productId}` | Remove um produto específico da lista do cliente. |
| `GET` | `/wishlist/{clientId}` | Retorna a lista completa de produtos daquele cliente. |
