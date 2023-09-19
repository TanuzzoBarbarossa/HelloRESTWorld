package com.generation.HelloRESTWorld.controllers;

import com.generation.HelloRESTWorld.dtos.StudentDto;
import com.generation.HelloRESTWorld.model.Student;
import com.generation.HelloRESTWorld.model.services.abstractions.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController //uno dei controller dell'applicazione
public class StudentController {

    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping(value = "/student") //getMapping dice a spring quando invocare questo metodo (di questo http)
    public ResponseEntity<Iterable <StudentDto>> getAllStudents(){
        //var (tipo di dato dedotto) in java vuol dire che lasci che il programma decide il suo variabile (ritorna iterable<Student>)
        List<StudentDto> studentDtos = new ArrayList<>();
        var students = studentService.getAllStudents();
        for(Student student : students){
                StudentDto sDto = new StudentDto(student.getId(), student.getFirstname() + "" + student.getLastname());
                studentDtos.add(sDto);
        }
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }
@GetMapping(value = "/student/{id}")

    public ResponseEntity<StudentDto> findById(@PathVariable long id){
        Optional<Student> studentOptional = studentService.findStudentById(id);
        if(studentOptional.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Student s = studentOptional.get();
        StudentDto sDto = new StudentDto(s.getId(), s.getFullname());
        return new ResponseEntity<>(sDto, HttpStatus.OK);
    }
}
