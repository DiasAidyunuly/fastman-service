ALTER TABLE checklist.checklists_head_tasks RENAME TO checklists_head;
ALTER TABLE checklist.checklists_head RENAME COLUMN id_head_task TO id_head;
ALTER TABLE checklist.checklists_head DROP COLUMN group_heads_day;
ALTER TABLE checklist.checklists_head DROP COLUMN group_heads_period;
ALTER TABLE checklist.checklists_head DROP COLUMN repeats_start;
ALTER TABLE checklist.checklists_head DROP COLUMN repeats_start_day;
ALTER TABLE checklist.checklists_head DROP COLUMN repeats_start_week;
ALTER TABLE checklist.checklists_head DROP COLUMN repeats_start_week_days;
ALTER TABLE checklist.checklists_head DROP COLUMN repeats_start_month;
ALTER TABLE checklist.checklists_head DROP COLUMN repeats_start_month_day;
ALTER TABLE checklist.checklists_head DROP COLUMN repeats_start_month_period;
ALTER TABLE checklist.checklists_head DROP COLUMN repeats_end;
ALTER TABLE checklist.checklists_head DROP COLUMN repeats_end_data;
ALTER TABLE checklist.checklists_head DROP COLUMN repeats_end_qty;
ALTER TABLE checklist.checklists_head DROP COLUMN end_date;
ALTER TABLE checklist.checklists_head DROP COLUMN color;
ALTER TABLE checklist.checklists_head ADD COLUMN qty_questions_total INT;
ALTER TABLE checklist.checklists_head ADD COLUMN qty_questions_done INT;



