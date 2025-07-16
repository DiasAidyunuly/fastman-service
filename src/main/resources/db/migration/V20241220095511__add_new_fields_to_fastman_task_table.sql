ALTER TABLE fastman.fastman_tasks
ADD COLUMN check_number VARCHAR(50),
ADD COLUMN check_date TIMESTAMPTZ,
ADD COLUMN check_price_manual FLOAT8;