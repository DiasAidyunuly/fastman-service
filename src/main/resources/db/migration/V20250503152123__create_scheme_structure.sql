CREATE SCHEMA IF NOT EXISTS structure AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS structure.head_structures (
    id_head_struсture  SERIAL primary key,
    code_head_structure bigint UNIQUE,
    name_head_struсture_short varchar(50),
    name_head_struсture_long varchar(100),
    str_level_head smallint,
    head_code_sprut varchar(50)
    );

CREATE TABLE IF NOT EXISTS structure.directions (
    id_direction  SERIAL primary key,
    code_direction bigint UNIQUE,
    name_direction_short varchar(50),
    name_direction_long varchar(100),
    str_level_direction smallint,
    parent_code_head bigint,
    direction_code_sprut varchar(50),
    value_reception_direction float8,
    value_critical_direction float8,
    active_direction boolean,
    CONSTRAINT directions_parent_code_head_fkey FOREIGN KEY (parent_code_head) REFERENCES structure.head_structures (code_head_structure)
    );

CREATE TABLE IF NOT EXISTS structure.departments (
    id_department  SERIAL primary key,
    code_department bigint UNIQUE,
    name_department_short varchar(50),
    name_department_long varchar(100),
    str_level_department smallint,
    parent_code_direction bigint,
    department_code_sprut varchar(50),
    value_reception_department float8,
    value_critical_department float8,
    active_department boolean,
    CONSTRAINT departments_parent_code_direction_fkey FOREIGN KEY (parent_code_direction) REFERENCES structure.directions (code_direction)
    );

CREATE TABLE IF NOT EXISTS structure.groups (
    id_group  SERIAL primary key,
    code_group bigint UNIQUE,
    name_group_short varchar(50),
    name_group_long varchar(100),
    str_level_group smallint,
    parent_code_department bigint,
    group_code_sprut varchar(50),
    value_reception_group float8,
    value_critical_group float8,
    active_group boolean,
    CONSTRAINT groups_parent_code_department_fkey FOREIGN KEY (parent_code_department) REFERENCES structure.departments (code_department)
    );

CREATE TABLE IF NOT EXISTS structure.subgroups (
    id_subgroup  SERIAL primary key,
    code_subgroup bigint UNIQUE,
    name_subgroup_short varchar(50),
    name_subgroup_long varchar(100),
    str_level_subgroup smallint,
    parent_code_group bigint,
    subgroup_code_sprut varchar(50),
    value_reception_subgroup float8,
    value_critical_subgroup float8,
    active_subgroup boolean,
    CONSTRAINT subgroups_parent_code_group_fkey FOREIGN KEY (parent_code_group) REFERENCES structure.groups (code_group)
    );