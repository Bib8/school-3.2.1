-- liquibase formatted sql

-- changeset admin:1
select name from student where name like '*%' group by name;
CREATE INDEX student_name_index ON student(name);

-- changeset admin:2
CREATE INDEX faculty_name_color_index ON faculty(name, color);
