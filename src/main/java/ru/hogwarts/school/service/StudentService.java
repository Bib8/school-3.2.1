package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public Student findStudentById(Long id){
        return studentRepository.findById(id).orElseThrow();
    }

    public Student editStudent(Student student){
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    public Collection<Student> findAllByAgeBetween(Integer minAge, Integer maxAge){
        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }
}
