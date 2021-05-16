CREATE TABLE pais (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE estado (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	codigo_pais BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_pais) REFERENCES pais(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cidade (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
  codigo_estado BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_estado) REFERENCES estado(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    rua VARCHAR(50) NOT NULL,
    numero VARCHAR(50) NOT NULL,
    complemento VARCHAR(50) NOT NULL,
	codigo_perfil BIGINT(20) NOT NULL,
	codigo_cidade BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pais (codigo, nome) VALUES(1, 'Brasil');
INSERT INTO pais (codigo, nome) VALUES(2, 'Alemanha');

INSERT INTO estado (codigo, nome, codigo_pais) VALUES(1, 'Acre', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(2, 'Alagoas', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(3, 'Amazonas', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(4, 'Amapá', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(5, 'Bahia', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(6, 'Ceará', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(7, 'Distrito Federal', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(8, 'Espírito Santo', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(9, 'Goiás', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(10, 'Maranhão', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(11, 'Minas Gerais', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(12, 'Mato Grosso do Sul', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(13, 'Mato Grosso', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(14, 'Pará', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(15, 'Paraíba', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(16, 'Pernambuco', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(17, 'Piauí', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(18, 'Paraná', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(19, 'Rio de Janeiro', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(20, 'Rio Grande do Norte', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(21, 'Rondônia', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(22, 'Roraima', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(23, 'Rio Grande do Sul', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(24, 'Santa Catarina', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(25, 'Sergipe', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(26, 'São Paulo', 1);
INSERT INTO estado (codigo, nome, codigo_pais) VALUES(27, 'Tocantins', 1);

INSERT INTO estado (codigo, nome, codigo_pais) VALUES(28, 'Frankfurt', 2);

INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (1, 'Belo Horizonte', 11);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (2, 'Uberlândia', 11);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (3, 'Uberaba', 11);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (4, 'São Paulo', 26);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (5, 'Campinas', 26);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (6, 'Rio de Janeiro', 19);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (7, 'Angra dos Reis', 19);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (8, 'Goiânia', 9);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (9, 'Caldas Novas', 9);

INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (10, 'Weiterstadt', 28);

INSERT INTO usuario (codigo, nome, email, senha, cpf, codigo_perfil, codigo_cidade, bairro, rua, numero, complemento) values (1, 'Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.','11111111111',1, 4, 'Brooklin', 'Rua Pensilvânia', '994', 'casa');

INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_ADMIN');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_COZINHEIRA');
INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_CLIENTE');
INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_ENTREGADOR');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);