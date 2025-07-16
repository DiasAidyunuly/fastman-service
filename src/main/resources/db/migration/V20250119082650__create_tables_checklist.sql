CREATE SCHEMA IF NOT EXISTS checklist AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS checklist.checklists_head_statuses (
    id_head_status SERIAL PRIMARY KEY,
    name_head_status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS checklist.checklists_templates (
    id_template SERIAL PRIMARY KEY,
    name_template VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS checklist.checklists_types_of_answers (
    id_type SERIAL PRIMARY KEY,
    name_type VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS checklist.checklists_question_statuses (
    id_question_status SERIAL PRIMARY KEY,
    name_question_status VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS checklist.checklists_head (
    id_head SERIAL PRIMARY KEY,
    name_head VARCHAR(100),
    id_template BIGINT,
    period_for_manual_tasks SMALLINT,
    only_the_creator boolean,
    need_planning boolean,
    photo_gallery boolean,
    role_performer VARCHAR(100),
    CONSTRAINT checklists_head_id_template_fkey FOREIGN KEY (id_template) REFERENCES checklist.checklists_templates (id_template)
);

CREATE TABLE IF NOT EXISTS checklist.checklists_chapter (
    id_chapter SERIAL PRIMARY KEY,
    name_chapter VARCHAR(100),
    id_head BIGINT,
    CONSTRAINT checklists_chapter_id_head_fkey FOREIGN KEY (id_head) REFERENCES checklist.checklists_head (id_head)
);

CREATE TABLE IF NOT EXISTS checklist.checklists_questions (
    id_question SERIAL PRIMARY KEY,
    id_head BIGINT,
    id_chapter BIGINT,
    id_type BIGINT,
    name_question VARCHAR(100),
    photo_resolution boolean,
    weight SMALLINT,
    photo_mandatory boolean,
    photo_maximum SMALLINT,
    comment_resolution boolean,
    photo_create VARCHAR(100),
    file_create VARCHAR(100),
    CONSTRAINT checklists_questions_id_head_fkey FOREIGN KEY (id_head) REFERENCES checklist.checklists_head (id_head),
    CONSTRAINT checklists_questions_id_chapter_fkey FOREIGN KEY (id_chapter) REFERENCES checklist.checklists_chapter (id_chapter),
    CONSTRAINT checklists_questions_id_type_fkey FOREIGN KEY (id_type) REFERENCES checklist.checklists_types_of_answers (id_type)
);

CREATE TABLE IF NOT EXISTS checklist.checklists_head_tasks (
    id_head_task SERIAL PRIMARY KEY,
    id_head_status BIGINT,
    id_head BIGINT,
    group_heads_day INT,
    group_heads_period INT,
    site INT,
    start_date TIMESTAMPTZ,
    start_end_date TIMESTAMPTZ,
    repeats_start VARCHAR(50),
    repeats_start_day SMALLINT,
    repeats_start_week SMALLINT,
    repeats_start_week_days VARCHAR(50),
    repeats_start_month SMALLINT,
    repeats_start_month_day VARCHAR(50),
    repeats_start_month_period VARCHAR(50),
    repeats_end VARCHAR(50),
    repeats_end_data TIMESTAMPTZ,
    repeats_end_qty SMALLINT,
    end_date TIMESTAMPTZ,
    role_creator VARCHAR(100),
    name_creator VARCHAR(100),
    tab_num_creator VARCHAR(50),
    comment_creator VARCHAR(150),
    color VARCHAR(50),
    CONSTRAINT checklists_head_tasks_id_head_status_fkey FOREIGN KEY (id_head_status) REFERENCES checklist.checklists_head_statuses (id_head_status),
    CONSTRAINT checklists_head_tasks_id_head_fkey FOREIGN KEY (id_head) REFERENCES checklist.checklists_head (id_head)
);

CREATE TABLE IF NOT EXISTS checklist.checklists_questions_tasks (
    id_questions_task SERIAL PRIMARY KEY,
    id_head_task BIGINT,
    id_question_status BIGINT,
    id_question BIGINT,
    answer_question VARCHAR(50),
    comment VARCHAR(100),
    weight_completion SMALLINT,
    photo_completion VARCHAR(100),
    CONSTRAINT checklists_questions_tasks_id_head_task_fkey FOREIGN KEY (id_head_task) REFERENCES checklist.checklists_head_tasks (id_head_task),
    CONSTRAINT checklists_questions_tasks_id_question_status_fkey FOREIGN KEY (id_question_status) REFERENCES checklist.checklists_question_statuses (id_question_status),
    CONSTRAINT checklists_questions_tasks_id_question_fkey FOREIGN KEY (id_question) REFERENCES checklist.checklists_questions (id_question)
);