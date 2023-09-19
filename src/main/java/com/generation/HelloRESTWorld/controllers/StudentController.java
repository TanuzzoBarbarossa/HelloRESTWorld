package com.generation.HelloRESTWorld.controllers;

import com.generation.HelloRESTWorld.model.Student;
import com.generation.HelloRESTWorld.model.services.abstractions.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RestController //uno dei controller dell'applicazione
public class StudentController {

    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping(value = "/student") //getMapping dice a spring quando invocare questo metodo (di questo http)
    public Iterable <Student> getAllStudents(){
        //var (tipo di dato dedotto) in java vuol dire che lasci che il programma decide il suo variabile (ritorna iterable<Student>)
        var students = studentService.getAllStudents();
        return students;
    }
}
