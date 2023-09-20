package com.generation.HelloRESTWorld.dtos;

import com.generation.HelloRESTWorld.model.Student;

import java.time.LocalDate;

public class StudentDto {
    private long id;
    private String fullname;

    public StudentDto(){
    }

    public StudentDto(long id, String fullname){
        this.id = id;
        this.fullname = fullname;
    }

    public StudentDto(Student s){
        this.id = s.getId();
        this.fullname = s.getFullname();
    }

    //costruttore vuoto
    public long getId() {
        return id;
    }



    public String getFullname() {
        return fullname;
    }

    public Student toStudent(){
        String[] tokens = fullname.split(" ");
        Student s = new Student(0,tokens[0], tokens[1], LocalDate.of(1000, 1, 1));
        return s;
    }
}
