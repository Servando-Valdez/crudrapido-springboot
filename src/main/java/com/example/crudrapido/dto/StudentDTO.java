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
}
