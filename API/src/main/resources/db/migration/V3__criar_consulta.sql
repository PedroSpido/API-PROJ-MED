CREATE TABLE consulta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    medico_id BIGINT,
    paciente_id BIGINT,
    data DATETIME,
    PRIMARY KEY (id),
    CONSTRAINT fk_consulta_medico FOREIGN KEY (medico_id) REFERENCES medico(id),
    CONSTRAINT fk_consulta_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id)
) ENGINE=InnoDB;
