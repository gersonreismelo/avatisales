# Projeto de Vendas

Este é um projeto de exemplo para gerenciar um sistema de vendas, incluindo itens, pessoas e transações de venda. A aplicação é construída com Spring Boot e inclui segurança básica para autenticação e autorização.

## Sumário

- [Descrição](#descrição)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração do Projeto](#configuração-do-projeto)
- [Uso](#uso)
- [Endpoints da API](#endpoints-da-api)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Descrição

Este projeto é uma aplicação de gerenciamento de vendas que permite a criação, leitura, atualização e exclusão de itens, pessoas e vendas. Ele inclui:

- Gerenciamento de **Itens**: Adicionar, atualizar, remover e listar itens disponíveis para venda.
- Gerenciamento de **Pessoas**: Adicionar, atualizar, remover e listar pessoas associadas às vendas.
- Gerenciamento de **Vendas**: Registrar, atualizar, remover e listar vendas realizadas.

A aplicação também possui configuração básica de segurança usando autenticação básica HTTP.

## Funcionalidades

- Cadastro de itens com informações como nome, marca, preço e saldo de estoque.
- Cadastro de pessoas com informações como nome, telefone e endereço.
- Registro de vendas associadas a itens e pessoas, com cálculo automático do valor total da venda.
- Segurança básica com autenticação HTTP.

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para desenvolvimento da aplicação.
- **Spring Security**: Configuração de segurança e autenticação.
- **JPA/Hibernate**: Mapeamento objeto-relacional e persistência de dados.
- **Lombok**: Redução de boilerplate code com anotações.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.

## Configuração do Projeto

1. **Clone o repositório**

   ```bash
   git clone https://github.com/gersonreismelo/avatisales
   ```

2. **Navegue até o diretório do projeto**

   ```bash
   cd avatisales
   ```

3. **Baixe as dependências**

   ```bash
   npm i
   ```

4. **Compile e execute a aplicação**

## Uso

A aplicação pode ser acessada através dos endpoints definidos na seção [Endpoints da API](#endpoints-da-api). Utilize ferramentas como Postman ou cURL para testar os endpoints. A autenticação básica é requerida para acessar os endpoints.

### Exemplo de Autenticação

Para acessar os endpoints protegidos, você pode usar a autenticação básica com as seguintes credenciais:

- **Usuário**: `user`
- **Senha**: `password`

### Exemplo com cURL

Para realizar uma requisição autenticada usando cURL, você pode usar o seguinte comando:

```bash
curl -u user:password http://localhost:8080/itens
```

### Exemplo com Postman

1. Abra o Postman e crie uma nova requisição.
2. No campo "URL", insira o endereço do endpoint (por exemplo, `http://localhost:8080/itens`).
3. Selecione o método HTTP apropriado (GET, POST, PUT, DELETE).
4. Vá até a aba "Authorization" e selecione o tipo "Basic Auth".
5. Insira as credenciais de usuário (`user`) e senha (`password`).
6. Envie a requisição.

## Endpoints da API

### Itens

- `GET /itens`: Lista todos os itens.
- `GET /itens/{id}`: Obtém um item por ID.
- `POST /itens`: Cria um novo item.
- `PUT /itens/{id}`: Atualiza um item existente.
- `DELETE /itens/{id}`: Remove um item por ID.

### Pessoas

- `GET /pessoas`: Lista todas as pessoas.
- `GET /pessoas/{id}`: Obtém uma pessoa por ID.
- `POST /pessoas`: Cria uma nova pessoa.
- `PUT /pessoas/{id}`: Atualiza uma pessoa existente.
- `DELETE /pessoas/{id}`: Remove uma pessoa por ID.

### Vendas

- `GET /vendas`: Lista todas as vendas.
- `GET /vendas/{id}`: Obtém uma venda por ID.
- `POST /vendas`: Cria uma nova venda.
- `PUT /vendas/{id}`: Atualiza uma venda existente.
- `DELETE /vendas/{id}`: Remove uma venda por ID.

## Contribuição

Se você deseja contribuir para o projeto, por favor, siga estes passos:

1. Faça um fork do repositório.
2. Crie uma nova branch para sua feature ou correção.
3. Faça suas alterações e adicione testes, se aplicável.
4. Envie um pull request com uma descrição clara das alterações.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
