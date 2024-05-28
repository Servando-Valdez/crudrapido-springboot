package com.example.crudrapido.service;

import com.example.crudrapido.dto.StudentDTO;
import com.example.crudrapido.dto.TeacherDTO;
import com.example.crudrapido.dto.TeacherResDTO;
import com.example.crudrapido.entity.Student;
import com.example.crudrapido.entity.Teacher;
import com.example.crudrapido.exceptionAdvice.DuplicateEmailException;
import com.example.crudrapido.exceptionAdvice.IdNotFoundException;
import com.example.crudrapido.mappers.TeacherMapper;
import com.example.crudrapido.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TeacherMapper teacherMapper;

    public List<TeacherResDTO> getAllTeachers(){
//        return this.teacherRepository.findAll();
        List<Teacher> teachers = this.teacherRepository.findAll();
        return teachers.stream()
                .map(teacherMapper::toDTO2)
                .collect(Collectors.toList());
    }

//    private TeacherDTO convertToDto(Teacher teacher) {
//        System.out.println(teacher);
//        TeacherDTO dto = new TeacherDTO(
//                teacher.getTeacherId(),
//                teacher.getFirstName(),
//                teacher.getLastName(),
//                teacher.getEmail()
//        );
//        return dto;
//    }

//    private TeacherResDTO convertToDtoResponse(Teacher teacher){
//        TeacherResDTO dto = new TeacherResDTO(
//                teacher.getTeacherId(),
//                teacher.getFirstName(),
//                teacher.getLastName(),
//                teacher.getEmail(),
//                teacher.getStudents()
//        );
//        return dto;
//    }

//    private Teacher convertToEntity(TeacherDTO teacher){
//        Teacher entity = new Teacher(
//                teacher.getTeacherId(),
//                teacher.getFirstName(),
//                teacher.getLastName(),
//                teacher.getEmail()
//        );
//        return entity;
//    }

    public TeacherDTO getTeacherById(Long id){
        Teacher teacher =  this.teacherRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException("Teacher not found with id: " + "id"));
        return this.teacherMapper.toDTO(teacher);
    }

    public TeacherDTO createTeacher(TeacherDTO teacher){
        String email = teacher.getEmail();
        if(this.teacherRepository.existsByEmail(email)){
            throw new DuplicateEmailException("email already exists");
        }
        Teacher teacherEntity = this.teacherRepository.save(this.teacherMapper.toEntity(teacher));
        return this.teacherMapper.toDTO(teacherEntity);
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO teacher){
        TeacherDTO currrentTeacher = this.getTeacherById(id);

        currrentTeacher.setEmail(teacher.getEmail());
        currrentTeacher.setFirstName(teacher.getFirstName());
        currrentTeacher.setLastName(teacher.getLastName());

        return this.teacherMapper.toDTO(this.teacherRepository.save(this.teacherMapper.toEntity(currrentTeacher)));
    }

    public void deleteTeacher(Long id){

        TeacherDTO teacher = this.getTeacherById(id);

        this.teacherRepository.deleteById(teacher.getTeacherId());
    }
}
