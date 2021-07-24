INSERT INTO Pessoa (id_pessoa, nome, cpf, acesso_usuario, acesso_senha) VALUES
  (1, 'Admin', '999.999.999-99', 'admin', 'admin') ON DUPLICATE KEY UPDATE id_pessoa = 1;

INSERT INTO Funcionario (id_pessoa, cargo, salario) VALUES (1, 0, 0) ON DUPLICATE KEY UPDATE id_pessoa = 1;