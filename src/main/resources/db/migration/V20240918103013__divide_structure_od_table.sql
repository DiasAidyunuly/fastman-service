DROP TABLE IF EXISTS public.structure_od;

CREATE TABLE IF NOT EXISTS division
(
    division_id   serial primary key,
    division_name varchar(50)
);

CREATE TABLE IF NOT EXISTS reg_dir
(
    reg_dir_id    serial primary key,
    reg_dir_name  varchar(100),
    reg_dir_tab   varchar(100),
    division_id   int4,
    CONSTRAINT reg_dir_division_id_fkey FOREIGN KEY (division_id) REFERENCES division (division_id)
);

CREATE TABLE IF NOT EXISTS ter_dir
(
    ter_dir_id    serial primary key,
    ter_dir_name  varchar(100),
    ter_dir_tab   varchar(100),
    region        varchar(50),
    reg_dir_id    int4,
    CONSTRAINT ter_dir_reg_dir_id_fkey FOREIGN KEY (reg_dir_id) REFERENCES reg_dir (reg_dir_id)
);

CREATE TABLE IF NOT EXISTS dir_fil
(
    dir_fil_id    serial primary key,
    dir_fil_name  varchar(100),
    dir_fil_tab   varchar(100),
    ter_dir_id    int4,
    code_fil      int4,
    open          int2,
    date_open     timestamptz,
    date_close    timestamptz,
    CONSTRAINT dir_fil_ter_dir_id_fkey FOREIGN KEY (ter_dir_id) REFERENCES ter_dir (ter_dir_id),
    CONSTRAINT dir_fil_code_fil_fkey FOREIGN KEY (code_fil) REFERENCES filials (code_fil)
);