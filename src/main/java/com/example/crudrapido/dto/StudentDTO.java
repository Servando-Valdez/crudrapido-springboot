package com.example.crudrapido.dto;

import com.example.crudrapido.entity.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDTO {

    private long studentId;

    @NotNull(message = "FirstName is mandatory")
    private String firstName;

    @NotNull(message = "LastName is mandatory")
    private String lastName;

    @Email(message = "This must be a valid email")
    @NotNull(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Teacher ID is mandatory")
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
