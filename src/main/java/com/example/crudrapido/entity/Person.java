package com.example.crudrapido.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Data
public abstract class Person {

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(){}
}
