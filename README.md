# desafio-back-southsystem

## Técnologias utilizadas:
1. Spring Boot
2. MySql
3. OpenFeign
4. Docker
5. Docker Compose

## Regras de negócio

### Criação de uma votação:
1. O tópico precisa obrigatóriamente existir.
2. O tópico não pode ter uma votação ativa no momento.

### Criação de um voto:
1. O cpf não pode ter votado anteriormente.
2. A votação deve estar ativa.
3. O cpf deve ser válido e apto para votar (user-info)

## Versionamento 

#### Todos os endpoints são versionados.

## Versionamento do banco de dados

#### Para manter a integridade do banco de dados, é utilizada a ferramenta Liquibase.

## Informações úteis
1. A aplicação conta com uma documentação Swagger, que pode ser acessada via http://localhost:8030/desafio-south/swagger-ui.html
2. Dados de acesso ao MySql:
> username: root
> 
> password: toor
> 
> port: 3306

3. Para ligar a aplicação: `docker-compose up`
4. Para desligar a aplicação: `docker-compose down`