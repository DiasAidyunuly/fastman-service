ALTER TABLE fastman.executions
    ADD COLUMN id_of_type BIGINT,
ADD CONSTRAINT executions_id_of_type_fkey FOREIGN KEY (id_of_type) REFERENCES fastman.types_of_executions (id_of_type);