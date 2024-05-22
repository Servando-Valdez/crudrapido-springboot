package com.example.crudrapido.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class Student extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long studentId;

//    @NotNull(message = "Email is mandatory")
//    private String firstName;
//    @NotNull(message = "LastName is mandatory")
//    private String lastName;
//
//    @Email(message = "This must be a valid email")
//    @NotNull(message = "Email is mandatory")
//    @Column(unique = true, nullable = false)
//    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher_id", nullable = false)
    private Teacher teacher;
}
