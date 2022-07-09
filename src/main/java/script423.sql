select student.name, student.age, faculty.name from student,faculty
    inner join student s on faculty.id = s.faculty_id;

select student.name from student left join avatar a on student.id = a.student_id;
