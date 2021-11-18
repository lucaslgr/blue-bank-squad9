# PROJETO BLUE BANK - SQUAD 9

## Rodar o projeto usando Docker
- Requisitos:
    - Maven
    - Docker e Docker Compose instalados
- Passo a passo:
    - Gere o <b>.jar</b> da aplicação executando o comando no terminal:
        - <b>mvn clean install -DskipTests</b>
    - Instancie e rode os containers executando o comando no terminal:
        - <b>docker-compose up --build --force-recreate</b>
        
## Diagrama DER do banco de dados da aplicação
<img src="./db/blue-bank-DB-DER.png" width="100%"/>