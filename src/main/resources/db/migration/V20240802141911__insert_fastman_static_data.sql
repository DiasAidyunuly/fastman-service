INSERT INTO fastman.templates (template_name, manual)
VALUES ('Проверки на месте', 0),
       ('Быстродел Минус лист', 0),
       ('Быстродел Виртуальные остатки', 0),
       ('Быстродел стоп-продажи активного ассортимента', 0),
       ('Быстродел стоп-продажи неактивного ассортимента', 0),
       ('Быстродел ТОП-300', 0),
       ('Быстродел Товары после OOS', 0),
       ('Магнум ТОП-1000 Проверка представленности', 0),
       ('Неисполненное действие Локальная корректировка', 0),
       ('Старт промо', 0),
       ('Магнум ТОП-200: Промо без продаж', 0),
       ('Сделать заказ', 0),
       ('Самостоятельное напоминание о заказе', 0),
       ('Фотоотчёт Пайда', 0),
       ('Проверки остатков', 0),
       ('Товарные проверки', 0);
    INSERT INTO fastman.actions (action_name)
    VALUES ('Напечатать ценник'),
           ('Выставить товар'),
           ('Напечатать ценник и выставить товар на полку'),
           ('Списать товар'),
           ('Заказать товар'),
           ('Локальная корректировка'),
           ('Недопоставка'),
           ('Закрыт к заказу'),
           ('Сезонный товар'),
           ('Товар на возврате/списание'),
           ('Некорректная цена'),
           ('Остаток корректный'),
           ('Заказ не требуется'),
           ('Товар отсутствует'),
           ('Не проведен приход по товару');
INSERT INTO fastman.statuses (status_code, status_name)
VALUES (1, 'В работе'),
       (2, 'Требуется действие'),
       (3, 'Завершено'),
       (4, 'Исправлено');
INSERT INTO fastman.incidents (incident_name, complete_day, template_id, quarantine, qty_task, action_id,
                               "disable", mon, tue, wed, thu, fri, sat, sun)
VALUES ('Топ-1000 проверка представленности', 1, 9, 0, NULL, 1, 0, 1, 1, 1, 1, 1, 1, 1),
       ('ТОП-200 ПРОМО', 3, 11, 10, NULL, NULL, 0, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO fastman.executions (execution_name)
VALUES ('Штрихкод товара'),
       ('Цена товара'),
       ('Фото товара на доп выкладке'),
       ('Фото на основной полке');
INSERT INTO fastman.inc_actions (action_id, incident_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (5, 1),
       (6, 1),
       (10, 1),
       (11, 1),
       (1, 2),
       (2, 2),
       (3, 2),
       (4, 2),
       (6, 2),
       (7, 2),
       (5, 2),
       (8, 2);
INSERT INTO fastman.inc_executions (incident_id, execution_id, mandatory, allow_check)
VALUES (1, 1, 1, 1),
       (1, 2, 1, 1),
       (1, 4, 1, 0);
INSERT INTO fastman.fastman_tasks (item_id, location_id, supplier_name, incident_id, status_id, action_id, "date", price, stock_qty, date_action, executor, description, date_create, "comment", date_end, change_date, user_change, date_completion, check_code, check_price, photo_completion, photo_create, file_task, date_check, task_check)
VALUES
    ('73391', 1135, 'АСТЫК ТОО', 1, 1, NULL, '2024-04-09', 500, 10, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
    ('73392', 1135, 'АСТЫК ТОО', 1, 1, NULL, '2024-04-09', 600, 20, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
    ('73393', 1135, 'АСТЫК ТОО', 1, 2, 1, '2024-04-09', 700, 30, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
    ('73394', 1135, 'АСТЫК ТОО', 1, 3, NULL, '2024-04-09', 800, 40, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', '2024-04-09 16:00:00.000 +0500', 'Петров', '2024-04-09 16:00:00.000 +0500', NULL, 800, NULL, NULL, NULL, NULL, NULL),
    ('73395', 1135, 'АСТЫК ТОО', 1, 4, 2, '2024-04-09', 900, 50, '2024-04-09 15:00:00.000 +0500', NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', '2024-04-09 16:00:00.000 +0500', 'Иванов', '2024-04-09 16:00:00.000 +0500', NULL, 950, NULL, NULL, NULL, NULL, NULL),
    ('73391', 1135, 'АСТЫК ТОО', 2, 1, NULL, '2024-04-09', 500, 60, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
    ('73392', 1135, 'АСТЫК ТОО', 2, 1, NULL, '2024-04-09', 600, -10, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
    ('73393', 1135, 'АСТЫК ТОО', 2, 3, NULL, '2024-04-09', 700, -20, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', '2024-04-09 16:00:00.000 +0500', 'Сидоров', '2024-04-09 16:00:00.000 +0500', NULL, 700, NULL, NULL, NULL, NULL, NULL),
    ('73391', 96, 'АСТЫК ТОО', 1, 1, NULL, '2024-04-09', 500, -30, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
    ('73392', 96, 'АСТЫК ТОО', 1, 2, 3, '2024-04-09', 600, 0, NULL, NULL, NULL, '2024-04-09 06:00:00.000 +0500', NULL, '2024-06-09 23:59:59.000 +0500', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
