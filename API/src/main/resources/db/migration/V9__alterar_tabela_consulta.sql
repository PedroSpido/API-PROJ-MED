ALTER TABLE consulta
ADD COLUMN cancelada BOOLEAN DEFAULT FALSE,
ADD COLUMN motivo_cancelamento_consulta VARCHAR(255);
