package com.example.crudrapido.dto;

import com.example.crudrapido.entity.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDTO {

    private long studentId;

    private String firstName;
    private String lastName;

    private String email;

    private Long teacher;

    public StudentDTO(long studentId, String firstName, String lastName, String email, Long teacher) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.teacher = teacher;
    }

    public StudentDTO(String firstName, String lastName, String email, Long teacher) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.teacher = teacher;
    }

    public StudentDTO(){}


}
