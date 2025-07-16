ALTER TABLE checklist.checklists_questions_tasks RENAME TO checklists_questions;
ALTER TABLE checklist.checklists_questions RENAME COLUMN id_head_task TO id_head;
ALTER TABLE checklist.checklists_questions ADD COLUMN id_type SMALLINT;


