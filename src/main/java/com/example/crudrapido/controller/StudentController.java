package com.example.crudrapido.controller;

import com.example.crudrapido.entity.Student;
import com.example.crudrapido.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAll(){
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<Student> getOneById(@PathVariable("id") Long id){
        return studentService.getStudent(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Student student){
        studentService.saveOrUpdate(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        studentService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @Valid @RequestBody Student student){
        studentService.saveOrUpdate(student);
    }
}
