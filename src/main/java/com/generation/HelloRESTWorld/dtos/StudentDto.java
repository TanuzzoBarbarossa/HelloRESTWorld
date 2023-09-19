package com.generation.HelloRESTWorld.dtos;

public class StudentDto {
    private long id;
    private String fullname;

    public StudentDto(){
    }

    public StudentDto(long id, String fullname){
        this.id = id;
        this.fullname = fullname;
    }

    public long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }
}
