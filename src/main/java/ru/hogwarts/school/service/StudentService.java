package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentFilters;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student addStudent(Student student){
        logger.debug("adding Student");
        return studentRepository.save(student);
    }

    public Student findStudentById(Long id){
        logger.debug("finding Student By Id");
        return studentRepository.findById(id).orElseThrow();
    }

    public Student editStudent(Student student){
        logger.debug("editing Student");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id){
        logger.debug("deleting Student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findAllByAgeBetween(Integer minAge, Integer maxAge){
        logger.debug("finding All By Age Between");
        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }

    public List<StudentFilters> getStudentQTY() {
        logger.debug("getting Student QTY");
        return studentRepository.getStudentQTY();
    }
    public List<StudentFilters> getStudentAVGAge() {
        logger.debug("getting Student AVG Age");
        return studentRepository.getStudentAVGAge();
    }
    public List<StudentFilters> getLastFiveStudentId() {
        logger.debug("getting Last Five Student Id");
        return studentRepository.getLastFiveStudentId();
    }

    public Collection<String> getFilteredByName() {
        return studentRepository.findAll().parallelStream().map(Student::getName).map(String::toUpperCase).filter(s -> s.startsWith("A")).sorted().collect(Collectors.toList());
    }

    public Double getAllStudentsAVGAgeSTR() {
        return studentRepository.findAll().stream().mapToInt(Student::getAge).average().orElse(0);
    }


}
