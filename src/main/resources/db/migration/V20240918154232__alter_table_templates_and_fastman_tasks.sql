ALTER TABLE fastman.templates RENAME COLUMN manual TO is_manual;

ALTER TABLE fastman.templates
ALTER COLUMN is_manual TYPE boolean
  USING CASE
    WHEN is_manual = 1 THEN TRUE
    WHEN is_manual = 0 THEN FALSE
      ELSE NULL
END;

ALTER TABLE fastman.fastman_tasks ADD COLUMN is_manual boolean;
INSERT INTO fastman.fastman_tasks (item_code, site_code, supplier_name, incident_id, status_id, action_id, "date", price, stock_qty, date_action, executor, description, date_create, "comment", date_end, change_date, user_change, date_completion, check_code, check_price, photo_completion, photo_create, file_task, date_check, task_check, is_manual)
VALUES
    ('73391', 1135, 'АСТЫК ТОО', 1, 1, NULL, '2024-04-09', 500, 10, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false),
    ('73392', 1135, 'АСТЫК ТОО', 1, 1, NULL, '2024-04-09', 600, 20, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false),
    ('73393', 1135, 'АСТЫК ТОО', 1, 2, 1, '2024-04-09', 700, 30, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false),
    ('73394', 1135, 'АСТЫК ТОО', 1, 3, NULL, '2024-04-09', 800, 40, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', '2024-04-09 16:00:00.000 +0500', 'Петров', '2024-04-09 16:00:00.000 +0500', NULL, 800, NULL, NULL, NULL, NULL, NULL, false),
    ('73395', 1135, 'АСТЫК ТОО', 1, 4, 2, '2024-04-09', 900, 50, '2024-04-09 15:00:00.000 +0500', NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', '2024-04-09 16:00:00.000 +0500', 'Иванов', '2024-04-09 16:00:00.000 +0500', NULL, 950, NULL, NULL, NULL, NULL, NULL, false),
    ('73391', 1135, 'АСТЫК ТОО', 2, 1, NULL, '2024-04-09', 500, 60, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false),
    ('73392', 1135, 'АСТЫК ТОО', 2, 1, NULL, '2024-04-09', 600, -10, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false),
    ('73393', 1135, 'АСТЫК ТОО', 2, 3, NULL, '2024-04-09', 700, -20, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', '2024-04-09 16:00:00.000 +0500', 'Сидоров', '2024-04-09 16:00:00.000 +0500', NULL, 700, NULL, NULL, NULL, NULL, NULL, false),
    ('73391', 96, 'АСТЫК ТОО', 1, 1, NULL, '2024-04-09', 500, -30, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false),
    ('73392', 96, 'АСТЫК ТОО', 1, 2, 3, '2024-04-09', 600, 0, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false);
