package ru.hogwarts.school.serviceTest;

import ru.hogwarts.school.model.Student;

public class StudentServiceTestImpl implements StudentServiceTestInterface {

    @Override
    public Student getStudent() {
        return new Student(1L, "boby", 15);
    }
}
