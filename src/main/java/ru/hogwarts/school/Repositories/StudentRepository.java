package ru.hogwarts.school.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.Model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findAllByAgeBetween(Integer maxAge, Integer minAge);
}
