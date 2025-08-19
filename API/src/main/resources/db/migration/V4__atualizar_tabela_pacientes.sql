ALTER TABLE paciente
    RENAME COLUMN endereco_logradouro TO logradouro,
    RENAME COLUMN endereco_numero TO numero,
    RENAME COLUMN endereco_complemento TO complemento,
    RENAME COLUMN endereco_bairro TO bairro,
    RENAME COLUMN endereco_cidade TO cidade,
    RENAME COLUMN endereco_uf TO uf,
    RENAME COLUMN endereco_cep TO cep;
