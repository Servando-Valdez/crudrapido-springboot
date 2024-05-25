package com.example.crudrapido.controller;

import com.example.crudrapido.dto.TeacherDTO;
import com.example.crudrapido.entity.Teacher;
import com.example.crudrapido.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDTO> getAll(){
//        List<Teacher> listTeacher = new ArrayList<>();
//
//        Teacher teacher = new Teacher();
//        teacher.setTeacherId(1);
//        teacher.setFirstName("teacher1");
//        teacher.setLastName("teacher1");
//        teacher.setEmail("teacher1@gmail.com");
//        listTeacher.add(teacher);
//        return listTeacher;
        return this.teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO GetById(@PathVariable("id") Long id){
        return this.teacherService.getTeacherById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDTO create(@Valid @RequestBody TeacherDTO teacher){
        return this.teacherService.createTeacher(teacher);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        this.teacherService.deleteTeacher(id);
    }
}
