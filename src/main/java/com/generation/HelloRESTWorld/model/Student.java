package com.generation.HelloRESTWorld.model;



import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    private LocalDate birthdate;

    //costruttore di default per jpa
    public Student(){

    }


    public Student(long id, String firstname, String lastname, LocalDate birthdate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

    public String getFullname(){
        return firstname + " " + lastname;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setId(long id) {
        this.id = id;
    }


}
