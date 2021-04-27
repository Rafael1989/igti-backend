CREATE TABLE perfil (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO perfil (nome) values ('ADMIN');
INSERT INTO perfil (nome) values ('COZINHEIRA');
INSERT INTO perfil (nome) values ('CLIENTE');
INSERT INTO perfil (nome) values ('ENTREGADOR');