--liquibase formatted sql

--changeset medeiros:1
CREATE TABLE assembleia.associado
(
    id   int NOT NULL AUTO_INCREMENT,
    cpf  VARCHAR(11),
    nome VARCHAR(100),
    PRIMARY KEY (id)
);

--changeset medeiros:2
CREATE TABLE assembleia.sessao
(
    id          int NOT NULL AUTO_INCREMENT,
    data_inicio DATETIME,
    duracao     TIME,
    pauta       VARCHAR(100),
    PRIMARY KEY (id)
);

--changeset medeiros:3
CREATE TABLE assembleia.voto
(
    associado_id int NOT NULL,
    sessao_id    int NOT NULL,
    aprovado     BIT,
    PRIMARY KEY (associado_id, sessao_id)
);

ALTER TABLE assembleia.voto ADD FOREIGN KEY ( associado_id ) REFERENCES  assembleia.associado ( id );
ALTER TABLE assembleia.voto ADD FOREIGN KEY ( sessao_id )  REFERENCES  assembleia.sessao ( id );