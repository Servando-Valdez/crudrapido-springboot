package com.example.crudrapido.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Override
    public String toString(){
        return String.valueOf(this.getTeacherId())+ "" + this.getFirstName() + this.getStudents();
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
