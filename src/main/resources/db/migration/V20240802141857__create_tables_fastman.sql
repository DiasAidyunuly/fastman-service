CREATE SCHEMA IF NOT EXISTS fastman AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS fastman.actions
(
    action_id   SERIAL PRIMARY KEY,
    action_name VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS fastman.levels
(
    level_id         SERIAL PRIMARY KEY,
    level_name       VARCHAR(50),
    level_short_name VARCHAR(10)
);
CREATE TABLE IF NOT EXISTS fastman.items
(
    item_id        SERIAL PRIMARY KEY,
    item_name      VARCHAR(50),
    level_code_0   INT,
    level_code_1   INT,
    level_code_2   INT,
    level_code_3   INT,
    unit_meas_name VARCHAR(50),
    change_date    TIMESTAMPTZ,
    CONSTRAINT items_level_code_0_fkey FOREIGN KEY (level_code_0) REFERENCES fastman.levels (level_id),
    CONSTRAINT items_level_code_1_fkey FOREIGN KEY (level_code_1) REFERENCES fastman.levels (level_id),
    CONSTRAINT items_level_code_2_fkey FOREIGN KEY (level_code_2) REFERENCES fastman.levels (level_id),
    CONSTRAINT items_level_code_3_fkey FOREIGN KEY (level_code_3) REFERENCES fastman.levels (level_id)
);
CREATE TABLE IF NOT EXISTS fastman.barcodes
(
    barcode_id  SERIAL PRIMARY KEY,
    item_id     BIGINT,
    change_date TIMESTAMPTZ,
    CONSTRAINT barcodes_item_id_fkey FOREIGN KEY (item_id) REFERENCES fastman.items (item_id)
);
CREATE TABLE IF NOT EXISTS fastman.executions
(
    execution_id   SERIAL PRIMARY KEY,
    execution_name VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS fastman.locations
(
    location_id SMALLINT PRIMARY KEY,
    site_code   INT,
    site_name   VARCHAR(50),
    format_name VARCHAR(50),
    change_date TIMESTAMPTZ
);
CREATE TABLE IF NOT EXISTS fastman.item_properties
(
    item_property_id SERIAL PRIMARY KEY,
    location_id      SMALLINT,
    item_id          BIGINT,
    apolloaddr       VARCHAR(50),
    CONSTRAINT item_properties_item_id_fkey FOREIGN KEY (item_id) REFERENCES fastman.items (item_id),
    CONSTRAINT item_properties_location_id_fkey FOREIGN KEY (location_id) REFERENCES fastman.locations (location_id)
);
CREATE TABLE IF NOT EXISTS fastman.statuses
(
    status_id   SERIAL PRIMARY KEY,
    status_code INT,
    status_name VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS fastman.templates
(
    template_id   SERIAL PRIMARY KEY,
    template_name VARCHAR(50),
    manual        SMALLINT
);
CREATE TABLE IF NOT EXISTS fastman.suppliers
(
    supplier_id   SERIAL PRIMARY KEY,
    location_id   SMALLINT,
    item_id       BIGINT,
    supplier_code INT,
    supplier_name VARCHAR(50),
    CONSTRAINT suppliers_item_id_fkey FOREIGN KEY (item_id) REFERENCES fastman.items (item_id),
    CONSTRAINT suppliers_location_id_fkey FOREIGN KEY (location_id) REFERENCES fastman.locations (location_id)
);
CREATE TABLE IF NOT EXISTS fastman.incidents
(
    incident_id   SERIAL PRIMARY KEY,
    incident_name VARCHAR(50),
    complete_day  INT,
    template_id   BIGINT,
    quarantine    SMALLINT,
    qty_task      INT,
    action_id     BIGINT,
    "disable"     SMALLINT,
    mon           SMALLINT,
    tue           SMALLINT,
    wed           SMALLINT,
    thu           SMALLINT,
    fri           SMALLINT,
    sat           SMALLINT,
    sun           SMALLINT,
    CONSTRAINT incidents_template_id_fkey FOREIGN KEY (template_id) REFERENCES fastman.templates (template_id),
    CONSTRAINT incidents_action_id_fkey FOREIGN KEY (action_id) REFERENCES fastman.actions (action_id)
);
CREATE TABLE IF NOT EXISTS fastman.site_exceptions
(
    site_exception_id SERIAL PRIMARY KEY,
    location_id       SMALLINT,
    incident_id       BIGINT,
    exc_qty_task      INT,
    CONSTRAINT site_exceptions_incident_id_fkey FOREIGN KEY (incident_id) REFERENCES fastman.incidents (incident_id)
);
CREATE TABLE IF NOT EXISTS fastman.inc_executions
(
    inc_execution_id SERIAL PRIMARY KEY,
    incident_id      BIGINT,
    execution_id     BIGINT,
    mandatory        SMALLINT,
    allow_check      SMALLINT,
    CONSTRAINT inc_executions_execution_id_fkey FOREIGN KEY (execution_id) REFERENCES fastman.executions (execution_id),
    CONSTRAINT inc_executions_incident_id_fkey FOREIGN KEY (incident_id) REFERENCES fastman.incidents (incident_id)
);
CREATE TABLE IF NOT EXISTS fastman.inc_actions
(
    inc_action_id SERIAL PRIMARY KEY,
    action_id     BIGINT,
    incident_id   BIGINT,
    CONSTRAINT inc_actions_action_id_fkey FOREIGN KEY (action_id) REFERENCES fastman.actions (action_id),
    CONSTRAINT inc_actions_incident_id_fkey FOREIGN KEY (incident_id) REFERENCES fastman.incidents (incident_id)
);

CREATE TABLE IF NOT EXISTS fastman.fastman_tasks
(
    fastman_task_id  SERIAL PRIMARY KEY,
    item_id          varchar(50),
    location_id      SMALLINT,
    supplier_name    varchar(100),
    incident_id      BIGINT,
    status_id        SMALLINT,
    action_id        BIGINT,
    "date"           DATE,
    price            FLOAT8,
    stock_qty        FLOAT8,
    date_action      TIMESTAMPTZ,
    executor         VARCHAR(50),
    description      TEXT,
    date_create      TIMESTAMPTZ,
    "comment"        varchar(100),
    date_end         TIMESTAMPTZ,
    change_date      TIMESTAMPTZ,
    user_change      TEXT,
    date_completion  TIMESTAMPTZ,
    check_code       INT,
    check_price      FLOAT8,
    photo_completion TEXT,
    photo_create     TEXT,
    file_task        TEXT,
    date_check       TIMESTAMPTZ,
    task_check       SMALLINT,
    CONSTRAINT fastman_task_action_id_fkey FOREIGN KEY (action_id) REFERENCES fastman.actions (action_id),
    CONSTRAINT fastman_task_incident_id_fkey FOREIGN KEY (incident_id) REFERENCES fastman.incidents (incident_id),
    CONSTRAINT fastman_task_status_id_fkey FOREIGN KEY (status_id) REFERENCES fastman.statuses (status_id)
);