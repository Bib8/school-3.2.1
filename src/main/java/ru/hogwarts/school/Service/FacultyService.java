package ru.hogwarts.school.Service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastId= 0;

    public Faculty addFaculty(Faculty faculty){
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFacultyById(long id){
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty){
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id){
        return faculties.remove(id);
    }

    public HashMap<Long, Faculty> filterColorFaculty(String color){
        return new HashMap<>(faculties.entrySet().
                stream().
                filter(x->color.equals(x.getValue().getColor())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}
