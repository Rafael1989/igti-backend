CREATE TABLE prato (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	quantidade VARCHAR(50) NOT NULL,
	codigo_cozinheira BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_cozinheira) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pedido (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	status VARCHAR(50) NOT NULL,
	codigo_cliente BIGINT(20),
    codigo_entregador BIGINT(20),
    FOREIGN KEY (codigo_cliente) REFERENCES usuario(codigo),
    FOREIGN KEY (codigo_entregador) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pedido_prato (
	codigo_pedido BIGINT(20) NOT NULL,
	codigo_prato BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_pedido, codigo_prato),
	FOREIGN KEY (codigo_pedido) REFERENCES pedido(codigo),
	FOREIGN KEY (codigo_prato) REFERENCES prato(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE venda (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_pedido BIGINT(20) NOT NULL,
	codigo_prato BIGINT(20) NOT NULL,
	quantidade VARCHAR(50) NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	codigo_cliente BIGINT(20),
    codigo_entregador BIGINT(20),
    codigo_cozinheira BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_cliente) REFERENCES usuario(codigo),
    FOREIGN KEY (codigo_entregador) REFERENCES usuario(codigo),
    FOREIGN KEY (codigo_cozinheira) REFERENCES usuario(codigo),
    FOREIGN KEY (codigo_pedido) REFERENCES pedido(codigo),
    FOREIGN KEY (codigo_prato) REFERENCES prato(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;