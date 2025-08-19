ALTER TABLE medico 
MODIFY COLUMN especialidade TINYINT,
ADD CHECK (especialidade BETWEEN 0 AND 3);