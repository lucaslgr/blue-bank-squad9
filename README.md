# PROJETO BLUE BANK - SQUAD 9

## Rodar o projeto usando Docker
- Requisitos:
    - Maven
    - Docker e Docker Compose instalados
- Passo a passo:
    - Gere o <b>.jar</b> da aplicação executando o comando no terminal:
        - ```
          mvn clean install -Dskiptests
          ```
    - Instancie e rode os containers executando o comando no terminal:
        - ```
            docker-compose up --build --force-recreate
          ```
## Para rodar o projeto utilizando outro arquivo de configuração de application.properties
- Para gerar o .jar vá até a pasta do projeto pelo terminal e rode o comando:
  -  ```
        mvn clean install -Dskiptests -Dspring.profiles.active=localdebug
     ```
- Para setar diretamente no Intellij:
  - <img src="./img/application-properties-param-intellij.png"/>
    
<b>OBS: O application.properties default está com as configurações para rodar utilizando os containers que são levantados no docker-compose.</b>
       
## Diagrama DER do banco de dados da aplicação
<img src="./db/blue-bank-DB-DER.png" width="100%"/>

## Acessar documentação [SWAGGER]
- Se estiver rodando localmente, acesse:
    ```
        http://localhost:8080/swagger-ui.html
    ```
- Se estiver rodando em algum servidor, acesse:
    ```
        <BASE_URL>/swagger-ui.html
    ```