DROP
DATABASE IF EXISTS db_bancoblue;
CREATE
DATABASE db_bancoblue;

USE
db_bancoblue;

CREATE TABLE clientes
(
    id_cliente         INTEGER UNSIGNED AUTO_INCREMENT NOT NULL,
    nome               VARCHAR(100) NOT NULL,
    sobrenome          VARCHAR(100) NOT NULL,
    cpf                VARCHAR(14)  NOT NULL,
    rg                 VARCHAR(13)  NOT NULL,
    data_de_nascimento DATE         NOT NULL,
    email              VARCHAR(150) NOT NULL,
    celular            VARCHAR(15)  NOT NULL,
    telefone           VARCHAR(15),
    senha              VARCHAR(255) NOT NULL,
    nome_do_pai        VARCHAR(255),
    nome_da_mae        VARCHAR(255) NOT NULL,
    profissao          VARCHAR(100) NOT NULL,
    renda_mensal       BIGINT UNSIGNED NOT NULL,
    patrimonio         BIGINT       NOT NULL,

    PRIMARY KEY (id_cliente),
    UNIQUE KEY (cpf),
    UNIQUE KEY (rg),
    UNIQUE KEY (email)
);

CREATE TABLE enderecos
(
    id_endereco INTEGER UNSIGNED AUTO_INCREMENT NOT NULL,
    cep         VARCHAR(9)   NOT NULL,
    logradouro  VARCHAR(255) NOT NULL,
    bairro      VARCHAR(100) NOT NULL,
    cidade      VARCHAR(100) NOT NULL,
    estado      VARCHAR(2)   NOT NULL,
    numero_casa VARCHAR(10)  NOT NULL,
    complemento VARCHAR(100),
    id_cliente  INTEGER UNSIGNED NOT NULL,

    PRIMARY KEY (id_endereco)
);

CREATE TABLE contas_corrente
(
    id_conta     INTEGER UNSIGNED AUTO_INCREMENT NOT NULL,
    numero       VARCHAR(20),
    agencia      VARCHAR(5),
    data_criacao DATETIME DEFAULT NOW(),
    senha        VARCHAR(255),
    saldo        BIGINT   DEFAULT 0,
    id_cliente   INTEGER UNSIGNED NOT NULL,

    PRIMARY KEY (id_conta)
);

CREATE TABLE transacoes
(
    id_transacao       INTEGER UNSIGNED AUTO_INCREMENT NOT NULL,
    valor              BIGINT UNSIGNED NOT NULL,
    id_conta_emissora  INTEGER UNSIGNED NOT NULL,
    id_conta_receptora INTEGER UNSIGNED NOT NULL,
    data_envio         DATETIME NOT NULL,
    data_recebimento   DATETIME NOT NULL,

    PRIMARY KEY (id_transacao)
);

-- SETANDO AS REFERENCIAS DE FOREIGN KEYS
ALTER TABLE db_bancoblue.enderecos
    ADD FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente);

ALTER TABLE db_bancoblue.contas_corrente
    ADD FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente);

ALTER TABLE db_bancoblue.transacoes
    ADD FOREIGN KEY (id_conta_emissora) REFERENCES contas_corrente (id_conta);

ALTER TABLE db_bancoblue.transacoes
    ADD FOREIGN KEY (id_conta_receptora) REFERENCES contas_corrente (id_conta);
