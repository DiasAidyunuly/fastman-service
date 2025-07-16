ALTER TABLE checklist.checklists_head RENAME TO checklist_head_temps;
ALTER TABLE checklist.checklist_head_temps RENAME COLUMN id_head TO id_head_temp;
ALTER TABLE checklist.checklists_questions RENAME COLUMN id_head TO id_head_temp;
ALTER TABLE checklist.checklists_chapter RENAME COLUMN id_head TO id_head_temp;
ALTER TABLE checklist.checklists_head_tasks RENAME COLUMN id_head TO id_head_temp;
ALTER TABLE checklist.checklists_questions RENAME TO checklist_question_temps;
ALTER TABLE checklist.checklists_types_of_answers
ADD COLUMN ui_element_type SMALLINT;