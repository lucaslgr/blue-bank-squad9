-- INSERINDO CLIENTES
-- As senhas dos clientes são todas 123456, apeenas para testes.
INSERT INTO `blue_bank`.`clientes` (`nome`, `sobrenome`, `cpf`, `rg`, `data_de_nascimento`, `email`, `celular`, `senha`, `nome_do_pai`, `nome_da_mae`, `profissao`, `renda_mensal`, `patrimonio`) VALUES ('Maria Eduarda', 'Gomes Vieira', '404.199.290-70', '13.371.095-6', '1988-05-10', 'maria_vieira@hotmail.com', '(32) 98409-4172', '$2a$12$fBYMXzkVon.cwzaA4gBB2OO2UHaIjrJ9KQrHEQFqY0jn7cUJSD86m', 'Zacarias Vieira da Silva', 'Isabel  Cristina Oliveira Gomes', 'Fonoaudiólogo', '203050', '3000000');
INSERT INTO `blue_bank`.`clientes` (`nome`, `sobrenome`, `cpf`, `rg`, `data_de_nascimento`, `email`, `celular`, `senha`, `nome_do_pai`, `nome_da_mae`, `profissao`, `renda_mensal`, `patrimonio`) VALUES ('João Pedro', 'da Rocha', '793.328.780-80', '10.778.376-9', '1990-11-25', 'pedro_rocha@hotmail.com', '(11) 97809-4192', '$2a$12$7qvQ3BdIt7ZHa68zoyQYbuLxyc933qGrxHmKVcHeo29oO7Vgzgeda', 'Antônio Pedro da Rocha', 'Solange Maria da Silva Rocha', 'Dentista', '245100', '500000');
INSERT INTO `blue_bank`.`clientes` (`nome`, `sobrenome`, `cpf`, `rg`, `data_de_nascimento`, `email`, `celular`, `senha`, `nome_do_pai`, `nome_da_mae`, `profissao`, `renda_mensal`, `patrimonio`) VALUES ('Matheus Daniel', 'Souza', '193.183.050-95', '10.965.199-6', '1991-01-05', 'matheussouza@hotmail.com', '(41) 92841-4242', '$2a$12$OkEdzNxv9jEKNO.8y0dXOuX4psyxcaVxsqgIRWFaTJvSD6j3iF3bu', 'Marcos da Silva Souza', 'Solange Berenice Souza', 'Professor', '250058', '0');

-- INSERINDO ENDEREÇOS
INSERT INTO `blue_bank`.`enderecos` (`cep`, `logradouro`, `bairro`, `cidade`, `estado`, `numero_casa`, `id_cliente`) VALUES ('47806-094', 'Av. Paraíba', 'Estados', 'João Pessoa', 'PB', '158', 1);
INSERT INTO `blue_bank`.`enderecos` (`cep`, `logradouro`, `bairro`, `cidade`, `estado`, `numero_casa`, `id_cliente`) VALUES ('58081-097', 'Rua Achilles Orlando Curtolo', 'Parque Industrial Tomas Edson', 'São Paulo', 'SP', '586', 2);
INSERT INTO `blue_bank`.`enderecos` (`cep`, `logradouro`, `bairro`, `cidade`, `estado`, `numero_casa`, `id_cliente`) VALUES ('85604-491', 'Napoleão Bonaparte', ' Bairro Alto', 'Curitiba', 'PR', '216', 3);

-- INSERINDO CONTAS
-- As senhas das contas são todas 123, apeenas para testes.
INSERT INTO `blue_bank`.`contas_corrente` (`numero`, `agencia`, `data_criacao`, `senha`, `saldo`, `id_cliente`) VALUES ('2885214654-7', '062454561', '2020-01-15', '$2a$12$8iGLVu3XQDoSGvucddsVaOPXTzGZz5tRNUZ.jfGgP8f/Di4o6oocO', '38500', 1);
INSERT INTO `blue_bank`.`contas_corrente` (`numero`, `agencia`, `data_criacao`, `senha`, `saldo`, `id_cliente`) VALUES ('82451-5', '31255616', '2019-11-10', '$2a$12$1sZqgM0EiARa2BHJuCpQB.If1jxLgk.Wi8VEVWL9gsgeW6BbDj.pq', '10580', 2);
INSERT INTO `blue_bank`.`contas_corrente` (`numero`, `agencia`, `data_criacao`, `senha`, `saldo`, `id_cliente`) VALUES ('07429709-2', '2156561', '2019-07-11', '$2a$12$pT1qzS02.ntRiUJgkqfYOOuCiKBjNX4GQ8bKKpFI.uewhgb6zKZL6', '5000', 3);

-- INSERINDO TRANSACOES
INSERT INTO `blue_bank`.`transacoes` (`valor`, `id_conta_emissora`, `id_conta_receptora`, `data_envio`, `data_recebimento`) VALUES ('5000', '1', '3', '2020-05-11', '2020-05-11');
INSERT INTO `blue_bank`.`transacoes` (`valor`, `id_conta_emissora`, `id_conta_receptora`, `data_envio`, `data_recebimento`) VALUES ('3500', '1', '2', '2020-06-05', '2020-06-05');
INSERT INTO `blue_bank`.`transacoes` (`valor`, `id_conta_emissora`, `id_conta_receptora`, `data_envio`, `data_recebimento`) VALUES ('89000', '2', '1', '2020-07-20', '2020-07-20');
INSERT INTO `blue_bank`.`transacoes` (`valor`, `id_conta_emissora`, `id_conta_receptora`, `data_envio`, `data_recebimento`) VALUES ('15000', '2', '3', '2020-08-01', '2020-08-01');
INSERT INTO `blue_bank`.`transacoes` (`valor`, `id_conta_emissora`, `id_conta_receptora`, `data_envio`, `data_recebimento`) VALUES ('6500', '3', '1', '2020-08-18', '2020-08-18');
INSERT INTO `blue_bank`.`transacoes` (`valor`, `id_conta_emissora`, `id_conta_receptora`, `data_envio`, `data_recebimento`) VALUES ('5000', '3', '2', '2020-09-11', '2020-09-11');
