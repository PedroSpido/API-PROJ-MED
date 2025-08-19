CREATE TABLE medico (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255),
    crm INT NOT NULL,
    especialidade TINYINT CHECK (especialidade BETWEEN 0 AND 3),
    ativo BIT DEFAULT 1,

    bairro VARCHAR(255),
    cep VARCHAR(255),
    cidade VARCHAR(255),
    complemento VARCHAR(255),
    logradouro VARCHAR(255),
    numero VARCHAR(255),
    uf VARCHAR(255)
);
