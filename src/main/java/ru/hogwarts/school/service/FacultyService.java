package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastId= 0;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

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
}
