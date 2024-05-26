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

    private Teacher convertToEntity(TeacherDTO teacher){
        Teacher entity = new Teacher(
                teacher.getTeacherId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail()
        );
        return entity;
    }

    public TeacherDTO getTeacherById(Long id){
        Teacher teacher =  this.teacherRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException("Teacher not found with id: " + "id"));
        return this.convertToDto(teacher);
    }

    public TeacherDTO createTeacher(TeacherDTO teacher){
        Teacher teacherEntity = this.teacherRepository.save(this.convertToEntity(teacher));
        return this.convertToDto(teacherEntity);
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO teacher){
        TeacherDTO currrentTeacher = this.getTeacherById(id);

        currrentTeacher.setEmail(teacher.getEmail());
        currrentTeacher.setFirstName(teacher.getFirstName());
        currrentTeacher.setLastName(teacher.getLastName());

        return this.convertToDto(this.teacherRepository.save(convertToEntity(currrentTeacher)));
    }

    public void deleteTeacher(Long id){

        TeacherDTO teacher = this.getTeacherById(id);

        this.teacherRepository.deleteById(teacher.getTeacherId());
    }
}
