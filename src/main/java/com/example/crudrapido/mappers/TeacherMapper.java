package com.example.crudrapido.mappers;

import com.example.crudrapido.dto.StudentDTO;
import com.example.crudrapido.dto.TeacherDTO;
import com.example.crudrapido.dto.TeacherResDTO;
import com.example.crudrapido.entity.Student;
import com.example.crudrapido.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    @Autowired
    private StudentMapper studentMapper;

    public TeacherResDTO toDTO2(Teacher teacher){

        return new TeacherResDTO(
                teacher.getTeacherId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getStudents().stream()
                        .map(studentMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }

    public TeacherDTO toDTO(Teacher teacher){
        return new TeacherDTO(
                teacher.getTeacherId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail()
        );
    }

    public Teacher toEntity(TeacherDTO teacher){
        return new Teacher(
                teacher.getTeacherId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail()
        );
    }
}
