CREATE TABLE prato (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	status VARCHAR(50) NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	quantidade VARCHAR(50) NOT NULL,
	codigo_cozinheira BIGINT(20) NOT NULL,
	codigo_cliente BIGINT(20),
	codigo_entregador BIGINT(20),
	FOREIGN KEY (codigo_cozinheira) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_cliente) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_entregador) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;