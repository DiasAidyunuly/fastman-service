CREATE TABLE IF NOT EXISTS fastman.execution_tasks
(
    execution_task_id   SERIAL PRIMARY KEY,
    fastman_task_id BIGINT,
    execution_id BIGINT,
    execution_value VARCHAR(100),
    CONSTRAINT execution_tasks_fastman_task_id_fkey FOREIGN KEY (fastman_task_id) REFERENCES fastman.fastman_tasks (fastman_task_id),
    CONSTRAINT execution_tasks_execution_id_fkey FOREIGN KEY (execution_id) REFERENCES fastman.executions (execution_id)
);