package com.example.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private long id;
    private boolean enableSigningIn=false;
    private long UserId;
    private String name;
    private LocalTime signIn;
    private LocalTime signOut;
    private String records;
}
