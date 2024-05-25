package com.example.crudrapido.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeacherDTO {
    private long teacherId;

    @NotNull(message = "FirstName is mandatory")
    private String firstName;

    @NotNull(message = "LastName is mandatory")
    private String lastName;

    @Email(message = "This must be a valid email")
    @NotNull(message = "Email is mandatory")
    private String email;

    public TeacherDTO(long teacherId, @NotNull(message = "FirstName is mandatory") String firstName, @NotNull(message = "LastName is mandatory") String lastName, @NotNull(message = "Email is mandatory") String email) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public TeacherDTO(@NotNull(message = "FirstName is mandatory") String firstName, @NotNull(message = "LastName is mandatory") String lastName, @NotNull(message = "Email is mandatory") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public TeacherDTO(){}
}
