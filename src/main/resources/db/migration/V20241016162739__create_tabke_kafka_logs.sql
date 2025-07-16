CREATE TABLE IF NOT EXISTS fastman.kafka_logs
(
    id          SERIAL PRIMARY KEY,
    status      SMALLINT,
    body        VARCHAR,
    date_create TIMESTAMPTZ,
    err_text    VARCHAR
);