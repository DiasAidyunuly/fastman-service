CREATE SCHEMA IF NOT EXISTS file AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS file.files
(
    file_id uuid primary key,
    file_name varchar(100),
    file_extension varchar(20),
    directory varchar(30),
    create_time timestamp,
    update_time timestamp,
    file_hash varchar(256)
);