package ru.gb.lesson7.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.lesson7.entities.Student;
import ru.gb.lesson7.repositories.StudentRepository;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentRepository repository;

    @GetMapping("/students")
    List<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/students/get/{id}")
    Student getById(@PathVariable Long id) {
        return repository.getById(id);
    }

    @GetMapping("/students/del/{id}")
    List<Student> delById(@PathVariable Long id) {
        repository.deleteById(id);
        return repository.findAll();
    }

    @GetMapping("/students/create")
    Student create(@PathParam("name") String name, @PathParam("age") Long age) {
        return repository.save(new Student(name, age.intValue()));
    }

}
