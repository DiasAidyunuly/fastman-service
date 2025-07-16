DROP TABLE IF EXISTS fastman.fastman_tasks;

CREATE TABLE IF NOT EXISTS fastman.fastman_tasks
(
    fastman_task_id  SERIAL PRIMARY KEY,
    item_code        varchar(50),
    site_code        SMALLINT,
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