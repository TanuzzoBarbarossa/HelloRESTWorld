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
    Student s1 = new Student(1, "Mario", "Rossi", LocalDate.of(2003,6,25));

    Student s2 = new Student(2, "Gigi", "Neri", LocalDate.of(2004, 5, 3));

        Collection<Student> students = new ArrayList<>();

        students.add(s1);
        students.add(s2);

        return students;
    }
}
