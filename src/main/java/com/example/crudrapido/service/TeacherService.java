package com.example.crudrapido.service;

import com.example.crudrapido.dto.StudentDTO;
import com.example.crudrapido.dto.TeacherDTO;
import com.example.crudrapido.entity.Student;
import com.example.crudrapido.entity.Teacher;
import com.example.crudrapido.exceptionAdvice.IdNotFoundException;
import com.example.crudrapido.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers(){
//        return this.teacherRepository.findAll();
        List<Teacher> teachers = this.teacherRepository.findAll();
        return teachers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TeacherDTO convertToDto(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO(
                teacher.getTeacherId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail()
        );
        return dto;
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
