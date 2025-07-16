ALTER TABLE checklist.checklist_question_temps RENAME COLUMN id_question TO id_question_temp;
ALTER TABLE checklist.checklists_questions RENAME COLUMN id_question TO id_question_temp;
ALTER TABLE checklist.checklists_chapter ADD COLUMN is_grouped boolean;