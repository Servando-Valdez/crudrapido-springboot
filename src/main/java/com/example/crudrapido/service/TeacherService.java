package com.example.crudrapido.service;

import com.example.crudrapido.entity.Teacher;
import com.example.crudrapido.exceptionAdvice.IdNotFoundException;
import com.example.crudrapido.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers(){
        return this.teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id){
        return this.teacherRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException("Student not found with id: " + "id"));
    }

    public Teacher createTeacher(Teacher teacher){
        return this.teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacher){
        Teacher currrentTeacher = this.getTeacherById(id);

        currrentTeacher.setEmail(teacher.getEmail());
        currrentTeacher.setFirstName(teacher.getFirstName());
        currrentTeacher.setLastName(teacher.getLastName());

        return this.teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id){
        Teacher teacher = this.getTeacherById(id);

        this.teacherRepository.delete(teacher);
    }
}
