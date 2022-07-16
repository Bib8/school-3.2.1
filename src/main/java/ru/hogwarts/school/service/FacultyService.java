package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastId= 0;
    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty addFaculty(Faculty faculty){
        logger.debug("adding Faculty");
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFacultyById(long id){
        logger.debug("finding Faculty By Id");
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty){
        logger.debug("editing Faculty");
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id){
        logger.debug("deleting Faculty");
        return faculties.remove(id);
    }

    public HashMap<Long, Faculty> filterColorFaculty(String color){
        logger.debug("filtering Color Faculty");
        return new HashMap<>(faculties.entrySet().
                stream().
                filter(x->color.equals(x.getValue().getColor())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public ResponseEntity<String> getFacultyNameWithMaxLength() {
        logger.info("Start search name with max length");
        Optional<String> maxLengthName = facultyRepository.findAll().stream()
                .map(Faculty::getName).max(Comparator.comparing(String::length));
        if (maxLengthName.isEmpty()) {
            logger.error("nothing here");
            return ResponseEntity.notFound().build();
        } else {
            logger.debug("max Length Name is {}", maxLengthName);
            return ResponseEntity.ok(maxLengthName.get());
        }

    }



}
