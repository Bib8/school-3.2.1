package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.HashMap;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;


    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id){
        Faculty faculty = facultyService.findFacultyById(id);
        if(faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public ResponseEntity<Faculty> addFaculty(@io.swagger.v3.oas.annotations.parameters.RequestBody Faculty faculty){
        Faculty addedFaculty = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(addedFaculty);
    }

    @PutMapping()
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.editFaculty(faculty);
        if (updatedFaculty == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("id")
    public ResponseEntity<Faculty> removeFaculty(@RequestParam Long id){
        Faculty removedFaculty = facultyService.deleteFaculty(id);
        if (removedFaculty == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(removedFaculty);
    }

    @GetMapping("color")
    public ResponseEntity<HashMap<Long, Faculty>> filterFacultyByColor(@RequestParam String color){
        return ResponseEntity.ok(facultyService.filterColorFaculty(color));
    }
     @GetMapping("/getMaxLengthName")
    public ResponseEntity<String> getMaxLengthName(){
         return facultyService.getFacultyNameWithMaxLength();
    }

}
