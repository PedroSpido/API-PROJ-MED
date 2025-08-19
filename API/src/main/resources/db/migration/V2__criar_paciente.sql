CREATE TABLE paciente (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255),
    cpf VARCHAR(255),
    ativo BIT DEFAULT TRUE,
    endereco_logradouro VARCHAR(255),
    endereco_numero VARCHAR(255),
    endereco_complemento VARCHAR(255),
    endereco_bairro VARCHAR(255),
    endereco_cidade VARCHAR(255),
    endereco_uf VARCHAR(255),
    endereco_cep VARCHAR(255)
);
