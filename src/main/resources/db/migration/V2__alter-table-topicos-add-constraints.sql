ALTER TABLE topicos
ADD CONSTRAINT unique_titulo_mensaje UNIQUE (titulo, mensaje);

CREATE INDEX idx_topicos_autor ON topicos (autor);
CREATE INDEX idx_topicos_curso ON topicos (curso);
CREATE INDEX idx_topicos_fecha ON topicos (fecha_creacion);