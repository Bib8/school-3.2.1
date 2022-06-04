package ru.hogwarts.school.Controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Model.Student;
import ru.hogwarts.school.Service.StudentService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Student>> getStudentInfo(@PathVariable Long id){
        Optional<Student> student = studentService.findStudentById(id);
        if(student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student addedStudent = studentService.addStudent(student);
        return ResponseEntity.ok(addedStudent);
    }

    @PutMapping()
    public ResponseEntity<Student> updateUser(@RequestBody Student student) {
        Student updatedStudent = studentService.editStudent(student);
        if (updatedStudent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("id")
    public ResponseEntity<Student> removeStudent(@RequestParam Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

   /* @GetMapping("age")
    public ResponseEntity<HashMap<Long, Student>> filterStudentsByAge(@RequestParam Integer age){
        return ResponseEntity.ok(studentService.filterStudent(age));
    }*/

    @GetMapping
    public ResponseEntity<Collection<Student>> findAllByAgeBetween(Integer maxAge, Integer minAge) {
        return ResponseEntity.ok(studentService.findAllByAgeBetween(minAge, maxAge));
    }
}
