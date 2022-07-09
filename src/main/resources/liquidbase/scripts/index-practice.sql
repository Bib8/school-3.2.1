-- liquibase formatted sql

-- changeset admin:1
CREATE INDEX student_name_index ON student(name);

-- changeset admin:2
CREATE INDEX faculty_name_color_index ON faculty(name, color);
