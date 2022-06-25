package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentFilters;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT name, count(*) as qty from Student group by name", nativeQuery = true)
    List<StudentFilters> getStudentQTY();

    @Query(value = "select age, AVG(age) as average from Student group by faculty_id", nativeQuery = true)
    List<StudentFilters> getStudentAVGAge();

    @Query(value = "select name from Student order by id desc limit 5", nativeQuery = true)
    List<StudentFilters> getLastFiveStudentId();
    Collection<Student> findAllByAgeBetween(Integer maxAge, Integer minAge);


}
