package com.example.crudrapido.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Person {

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;
}
