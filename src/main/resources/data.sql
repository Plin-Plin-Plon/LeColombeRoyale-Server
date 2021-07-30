INSERT INTO Pessoa (id_pessoa, nome, cpf, usuario, senha) VALUES
  (1, 'Admin', '999.999.999-99', 'admin', 'admin') ON DUPLICATE KEY UPDATE id_pessoa = 1;

INSERT INTO Funcionario (id_pessoa, cargo, salario) VALUES (1, 0, 0) ON DUPLICATE KEY UPDATE id_pessoa = 1;

drop table if exists role;
create table role (id_role bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id_role)) engine=MyISAM;

INSERT INTO role (id_role, description, name) VALUES (4, 'Admin role', 'ADMIN');
INSERT INTO role (id_role, description, name) VALUES (5, 'Mod role', 'MOD');
INSERT INTO role (id_role, description, name) VALUES (6, 'User role', 'USER');

drop table if exists user_roles;
create table user_roles (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id)) engine=MyISAM;

INSERT INTO user_roles (user_id, role_id) VALUES (1, 4);