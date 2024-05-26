package com.example.crudrapido.dto;

import com.example.crudrapido.entity.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TeacherResDTO {
    private long teacherId;

    @NotNull(message = "FirstName is mandatory")
    private String firstName;

    @NotNull(message = "LastName is mandatory")
    private String lastName;

    @Email(message = "This must be a valid email")
    @NotNull(message = "Email is mandatory")
    private String email;
    @NotNull
    private List<StudentDTO> students;

    public TeacherResDTO(long teacherId, String firstName, String lastName, String email, List<StudentDTO> students) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.students = students;
    }
}
