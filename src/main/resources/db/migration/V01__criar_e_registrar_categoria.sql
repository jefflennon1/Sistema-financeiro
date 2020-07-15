CREATE TABLE categoria(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Supermercado');
INSERT INTO categoria (nome) VALUES ('Fermácia');
INSERT INTO categoria (nome) VALUES ('Outros');
INSERT INTO categoria (nome) VALUES ('Gastos com restaurante');
INSERT INTO categoria (nome) VALUES ('Gastos em shopping');
INSERT INTO categoria (nome) VALUES ('Viagens');
INSERT INTO categoria (nome) VALUES ('Utensílios de casa');
