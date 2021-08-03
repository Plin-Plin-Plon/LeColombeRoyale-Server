INSERT INTO Pessoa (id_pessoa, nome, cpf, usuario, senha) VALUES
  (0, 'Admin', '999.999.999-99', 'admin', '$2a$10$.U6d4K7bEpfAlFxe2v9INetgijlOt0t0FMJdrLcmPWojnUde1QRa.') ON DUPLICATE KEY UPDATE id_pessoa = 0;

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