CREATE DATABASE db_bancoblue;

USE db_bancoblue;

CREATE TABLE enderecos (
	id_endereco INTEGER AUTO_INCREMENT,
	cep VARCHAR(9) NOT NULL,
	logradouro VARCHAR(255)NOT NULL,
	bairro VARCHAR(100) NOT NULL,
	cidade VARCHAR(100) NOT NULL,
	estado VARCHAR(2) NOT NULL,
	numero_casa VARCHAR(10) NOT NULL,
	complemento VARCHAR(100),
	pais VARCHAR(50) NOT NULL,
	
	PRIMARY KEY (id_endereco)
);

CREATE TABLE clientes (
	id_cliente INTEGER AUTO_INCREMENT, 
	nome VARCHAR(100) NOT NULL,
	sobrenome VARCHAR(100) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	rg VARCHAR(10) NOT NULL,
	data_de_nascimento DATE NOT NULL,
	email VARCHAR(255) NOT NULL,
	celular VARCHAR(15) NOT NULL,
	telefone VARCHAR(15),
	senha VARCHAR(255) NOT NULL,
	nome_do_pai VARCHAR(255),
	nome_da_mae VARCHAR(255) NOT NULL,
	profissao VARCHAR(100) NOT NULL,
	renda_mensal BIGINT NOT NULL,
	patrimonio BIGINT NOT NULL,
	id_endereco INTEGER NOT NULL, 
	id_endereco_cobranca INTEGER NOT NULL , 
	id_conta INTEGER NOT NULL,
	
	PRIMARY KEY (id_cliente),
	UNIQUE KEY (cpf),
	UNIQUE KEY (rg),
	UNIQUE KEY (email)
);

CREATE TABLE contas_corrente (
	id_conta INTEGER AUTO_INCREMENT,
	numero VARCHAR(20),
	agencia VARCHAR(5),
	data_criacao DATETIME DEFAULT NOW(),
	senha VARCHAR(255),
	saldo BIGINT,
	
	PRIMARY KEY (id_conta)
);

CREATE TABLE transacoes (
	id_transacao INTEGER AUTO_INCREMENT,
	valor BIGINT NOT NULL,
	id_cliente_emissor INTEGER NOT NULL, 
	id_cliente_receptor INTEGER NOT NULL, 
	data_envio DATETIME NOT NULL,
	data_recebimento DATETIME NOT NULL,
	PRIMARY KEY (id_transacao)
);

#SETANDO AS REFERENCIAS DE FOREIGN KEYS
ALTER TABLE db_bancoblue.clientes ADD FOREIGN KEY (id_endereco) REFERENCES enderecos(id_endereco);
ALTER TABLE db_bancoblue.clientes ADD FOREIGN KEY (id_endereco_cobranca) REFERENCES enderecos(id_endereco);
ALTER TABLE db_bancoblue.clientes ADD FOREIGN KEY (id_conta) REFERENCES contas_corrente(id_conta);

ALTER TABLE db_bancoblue.transacoes ADD FOREIGN KEY (id_cliente_emissor) REFERENCES clientes(id_cliente);
ALTER TABLE db_bancoblue.transacoes ADD FOREIGN KEY (id_cliente_receptor) REFERENCES clientes(id_cliente);
