package ru.hogwarts.school.Service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Model.Student;
import ru.hogwarts.school.Repositories.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public Optional<Student> findStudentById(long id){
        return studentRepository.findById(id);
    }

    public Student editStudent(Student student){
        return studentRepository.save(student);
    }

    public void deleteStudent(long id){
        studentRepository.deleteById(id);
    }

    public Collection<Student> findAllByAgeBetween(Integer minAge, Integer maxAge){
        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }
}
