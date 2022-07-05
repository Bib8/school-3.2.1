package ru.hogwarts.school.model;

import java.util.List;

public interface StudentFilters {
    Integer getStudentQTY();
    Integer getStudentAVGAge();
    List<Student> getLastFiveStudentId();
}
