package com.example.crudrapido.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@Data
//@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "teacher")
public class Teacher extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private long teacherId;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    public Teacher(long teacherId, String firstName, String LastName, String email){
        super(firstName, LastName, email);
        this.teacherId = teacherId;
    }

    public Teacher(String firstName, String LastName, String email){
        super(firstName, LastName, email);
    }

    public Teacher(){
        super();
    }


    //    @NotNull
//    private String firstName;
//
//    @NotNull
//    private String lastName;
//
//    @Email
//    @NotNull
//    @Column(nullable = false, unique = true)
//    private String email;
}
