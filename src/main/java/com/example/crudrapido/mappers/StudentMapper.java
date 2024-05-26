package com.example.crudrapido.mappers;

import com.example.crudrapido.dto.StudentDTO;
import com.example.crudrapido.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDTO toDTO(Student student){
        return new  StudentDTO(
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getTeacher().getTeacherId()
        );
    }

    public Student toEntity(StudentDTO student){
        return new Student(
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
}
