CREATE TABLE produto(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR (100) NOT NULL,
    categoria VARCHAR (100) NOT NULL,
    quantidade INT (100) NOT NULL,
    validade VARCHAR (100) NOT NULL,
    marca VARCHAR (100) NOT NULL,
    
    PRIMARY KEY (id)
);