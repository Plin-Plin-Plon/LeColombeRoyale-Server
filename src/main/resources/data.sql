INSERT INTO Pessoa (id_pessoa, nome, cpf, usuario, senha) VALUES
  (0, 'Admin', '999.999.999-99', 'admin', '$2a$10$.U6d4K7bEpfAlFxe2v9INetgijlOt0t0FMJdrLcmPWojnUde1QRa.') ON DUPLICATE KEY UPDATE id_pessoa = 0;

INSERT INTO Pessoa (id_pessoa, nome, cpf, usuario, senha, id_contato) VALUES
  (1, 'Henrique Fogaça', '111.111.111-11', 'LeChef', '$2a$10$POCpIfU0cO3zRXN3Y6S2tebw.1n/A.lndnuWderifsoEZL/xJhHi6', 1) ON DUPLICATE KEY UPDATE id_pessoa = 1;
INSERT INTO Endereco (id_endereco, bairro, cep, cidade, estado, logradouro, numero, id_pessoa) VALUES
  (1, 'Morumbi', '05502-001', 'São Paulo', 'São Paulo', 'Avenida Magalhães de Castro', 12000, 1) ON DUPLICATE KEY UPDATE id_endereco = 1;
INSERT INTO Contato (id_contato, email, tel_celular, tel_residencial) VALUES
  (1, 'SalGastronomia@gmail.com', '(11) 93198-9505', '(11) 3198-9505') ON DUPLICATE KEY UPDATE id_contato = 1;
INSERT INTO Funcionario (cargo, salario, id_pessoa) VALUES
  (1, 15000.00, 1) ON DUPLICATE KEY UPDATE id_pessoa = 1;

INSERT INTO Pessoa (id_pessoa, nome, cpf, usuario, senha, id_contato) VALUES
  (2, 'Lineu Silva', '222.222.222-22', 'Lineuzinho123', '$2a$10$POCpIfU0cO3zRXN3Y6S2tebw.1n/A.lndnuWderifsoEZL/xJhHi6', 2) ON DUPLICATE KEY UPDATE id_pessoa = 2;
INSERT INTO Endereco (id_endereco, bairro, cep, cidade, estado, logradouro, numero, id_pessoa) VALUES
  (2, 'Copacabana', '24597-123', 'Rio de Janeiro', 'Rio de Janeiro', 'Rua Flamengo', 1234, 2) ON DUPLICATE KEY UPDATE id_endereco = 2;
INSERT INTO Contato (id_contato, email, tel_celular, tel_residencial) VALUES
  (2, 'Lineu@gmail.com', '(12) 93198-9505', '(12) 3198-9505') ON DUPLICATE KEY UPDATE id_contato = 2;
INSERT INTO Hospede (premium, id_pessoa) VALUES
  (false, 2) ON DUPLICATE KEY UPDATE id_pessoa = 2;

INSERT INTO Pessoa (id_pessoa, nome, cpf, usuario, senha, id_contato) VALUES
  (3, 'Agostinho Carrara', '333.333.333-33', 'Agostinho123', '$2a$10$POCpIfU0cO3zRXN3Y6S2tebw.1n/A.lndnuWderifsoEZL/xJhHi6', 3) ON DUPLICATE KEY UPDATE id_pessoa = 3;
INSERT INTO Endereco (id_endereco, bairro, cep, cidade, estado, logradouro, numero, id_pessoa) VALUES
  (3, 'Copacabana', '24597-124', 'Rio de Janeiro', 'Rio de Janeiro', 'Rua Vasco da Gama', 2341, 3) ON DUPLICATE KEY UPDATE id_endereco = 3;
INSERT INTO Contato (id_contato, email, tel_celular, tel_residencial) VALUES
  (3, 'Agostinho@gmail.com', '(13) 93198-9505', '(13) 3198-9505') ON DUPLICATE KEY UPDATE id_contato = 3;
INSERT INTO Hospede (premium, id_pessoa) VALUES
  (true, 3) ON DUPLICATE KEY UPDATE id_pessoa = 3;

INSERT INTO Pessoa (id_pessoa, nome, cpf, usuario, senha, id_contato) VALUES
  (4, 'Bebel', '444.444.444-44', 'Bebelzinha123', '$2a$10$POCpIfU0cO3zRXN3Y6S2tebw.1n/A.lndnuWderifsoEZL/xJhHi6', 4) ON DUPLICATE KEY UPDATE id_pessoa = 4;
INSERT INTO Endereco (id_endereco, bairro, cep, cidade, estado, logradouro, numero, id_pessoa) VALUES
  (4, 'Copacabana', '24597-125', 'Rio de Janeiro', 'Rio de Janeiro', 'Rua Fluminense', 2351, 4) ON DUPLICATE KEY UPDATE id_endereco = 4;
INSERT INTO Contato (id_contato, email, tel_celular, tel_residencial) VALUES
  (4, 'Bebel@gmail.com', '(14) 93198-9505', '(14) 3198-9505') ON DUPLICATE KEY UPDATE id_contato = 4;
INSERT INTO Hospede (premium, id_pessoa) VALUES
  (true, 4) ON DUPLICATE KEY UPDATE id_pessoa = 4;

INSERT INTO Pessoa (id_pessoa, nome, cpf, usuario, senha, id_contato) VALUES
  (5, 'Medonça', '555.555.555-55', 'Medonçinha123', '$2a$10$POCpIfU0cO3zRXN3Y6S2tebw.1n/A.lndnuWderifsoEZL/xJhHi6', 5) ON DUPLICATE KEY UPDATE id_pessoa = 5;
INSERT INTO Endereco (id_endereco, bairro, cep, cidade, estado, logradouro, numero, id_pessoa) VALUES
  (5, 'Copacabana', '24597-126', 'Rio de Janeiro', 'Rio de Janeiro', 'Rua Botafogo', 2342, 5) ON DUPLICATE KEY UPDATE id_endereco = 5;
INSERT INTO Contato (id_contato, email, tel_celular, tel_residencial) VALUES
  (5, 'Medonça@gmail.com', '(15) 93198-9505', '(15) 3198-9505') ON DUPLICATE KEY UPDATE id_contato = 5;
INSERT INTO Hospede (premium, id_pessoa) VALUES
  (true, 5) ON DUPLICATE KEY UPDATE id_pessoa = 5;

INSERT INTO Role (id_role, description, name) 
    VALUES (1, 'Admin role', 'ADMIN') ON DUPLICATE KEY UPDATE id_role = 1;
INSERT INTO Role (id_role, description, name) 
    VALUES (2, 'Mod role', 'MOD') ON DUPLICATE KEY UPDATE id_role = 2;
INSERT INTO Role (id_role, description, name) 
    VALUES (3, 'User role', 'USER') ON DUPLICATE KEY UPDATE id_role = 3;

CREATE TABLE IF NOT EXISTS user_roles (
    user_id bigint NOT NULL, 
    role_id bigint NOT NULL, 
    col2 int(3) unsigned zerofill DEFAULT NULL,
    PRIMARY KEY(user_id, role_id)
)  ENGINE = MyISAM;

INSERT INTO user_roles (user_id, role_id) VALUES (0, 1) ON DUPLICATE KEY UPDATE user_id = 0;
INSERT INTO user_roles (user_id, role_id) VALUES (0, 2) ON DUPLICATE KEY UPDATE user_id = 0;
INSERT INTO user_roles (user_id, role_id) VALUES (0, 3) ON DUPLICATE KEY UPDATE user_id = 0;
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2) ON DUPLICATE KEY UPDATE user_id = 1;
INSERT INTO user_roles (user_id, role_id) VALUES (1, 3) ON DUPLICATE KEY UPDATE user_id = 1;
INSERT INTO user_roles (user_id, role_id) VALUES (2, 3) ON DUPLICATE KEY UPDATE user_id = 2;
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3) ON DUPLICATE KEY UPDATE user_id = 3;
INSERT INTO user_roles (user_id, role_id) VALUES (4, 3) ON DUPLICATE KEY UPDATE user_id = 4;
INSERT INTO user_roles (user_id, role_id) VALUES (5, 3) ON DUPLICATE KEY UPDATE user_id = 5;

INSERT INTO Servico (id_servico, nome, descricao, preco) VALUES
(1, "Bife à parmegiana", "Parmegiana de carne com molho pomodoro", 120.00) ON DUPLICATE KEY UPDATE id_servico = 1;
INSERT INTO Servico (id_servico, nome, descricao, preco) VALUES
(2, "Pizza de calabresa", "Pizza com calabresa, molho de tomate, cebola, e cheiro verde", 80.00) ON DUPLICATE KEY UPDATE id_servico = 2;
INSERT INTO Servico (id_servico, nome, descricao, preco) VALUES
(3, "Lasanha à bolonhesa", "Lasanha de 750g com molho pomodoro e carne moída", 70.00) ON DUPLICATE KEY UPDATE id_servico = 3;
INSERT INTO Servico (id_servico, nome, descricao, preco) VALUES
(4, "X-Bacon", "Lanche com carne de 110g, bacon, queijo mussarela, ketchup, alface e tomate", 45.00) ON DUPLICATE KEY UPDATE id_servico = 4;
INSERT INTO Servico (id_servico, nome, descricao, preco) VALUES
(5, "Salada Caesar", "Salada preparada com alface romana e molho Caesar", 38.00) ON DUPLICATE KEY UPDATE id_servico = 5;
INSERT INTO Servico (id_servico, nome, descricao, preco) VALUES
(6, "Strogonoff de frango", "Strogonoff de frango com batata palha e arroz branco", 65.00) ON DUPLICATE KEY UPDATE id_servico = 6;
INSERT INTO Servico (id_servico, nome, descricao, preco) VALUES
(7, "Nhoque", "Nhoque de 500g com molho ao sugo e queijo ralado", 60.00) ON DUPLICATE KEY UPDATE id_servico = 7;
INSERT INTO Servico (id_servico, nome, descricao, preco) VALUES
(8, "Salmão grelhado", "Salmão grelhado com molho shoyo", 150.00) ON DUPLICATE KEY UPDATE id_servico = 8;
INSERT INTO Servico (id_servico, nome, descricao, preco) VALUES
(9, "Bolo de chocolate", "Bolo de chocolate feito com a receita exatamente igual a do livro", 42.00) ON DUPLICATE KEY UPDATE id_servico = 9;

INSERT INTO Quarto (numero, tipo, vago, valor) VALUES
(101, "Solteiro", true, 150.00) ON DUPLICATE KEY UPDATE numero = 101;
INSERT INTO Quarto (numero, tipo, vago, valor) VALUES
(102, "Solteiro", true, 150.00) ON DUPLICATE KEY UPDATE numero = 102;
INSERT INTO Quarto (numero, tipo, vago, valor) VALUES
(103, "Solteiro", true, 150.00) ON DUPLICATE KEY UPDATE numero = 103;
INSERT INTO Quarto (numero, tipo, vago, valor) VALUES
(201, "Casal", true, 300.00) ON DUPLICATE KEY UPDATE numero = 201;
INSERT INTO Quarto (numero, tipo, vago, valor) VALUES
(202, "Casal", true, 300.00) ON DUPLICATE KEY UPDATE numero = 202;
INSERT INTO Quarto (numero, tipo, vago, valor) VALUES
(203, "Casal", true, 300.00) ON DUPLICATE KEY UPDATE numero = 203;
INSERT INTO Quarto (numero, tipo, vago, valor) VALUES
(301, "Familia", true, 450.00) ON DUPLICATE KEY UPDATE numero = 301;
INSERT INTO Quarto (numero, tipo, vago, valor) VALUES
(302, "Familia", true, 450.00) ON DUPLICATE KEY UPDATE numero = 302;
INSERT INTO Quarto (numero, tipo, vago, valor) VALUES
(303, "Familia", true, 450.00) ON DUPLICATE KEY UPDATE numero = 303;