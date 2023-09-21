package com.generation.HelloRESTWorld.controllers;

import com.generation.HelloRESTWorld.dtos.StudentDto;
import com.generation.HelloRESTWorld.model.Student;
import com.generation.HelloRESTWorld.model.services.abstractions.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/student") //getMapping dice a spring quando invocare questo metodo (di questo http)
    public ResponseEntity<Iterable<StudentDto>> getAllStudents() {
        //var (tipo di dato dedotto) in java vuol dire che lasci che il programma decide il suo variabile (ritorna iterable<Student>)
        List<StudentDto> studentDtos = new ArrayList<>();
        var students = studentService.getAllStudents();
        for (Student student : students) {
            StudentDto sDto = new StudentDto(student.getId(), student.getFullname());
            studentDtos.add(sDto);
        }
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable long id) { //response entit
        //Student s = studentService.findStudentById(id);
        //s.getFirstname();
        Optional<Student> studentOptional = studentService.findStudentById(id);
        //Student s = studentOptional.get();
        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Student s = studentOptional.get();
        StudentDto sDto = new StudentDto(s.getId(), s.getFullname());
        return new ResponseEntity<>(sDto, HttpStatus.OK);
    }

    @PostMapping(value = "/student")
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto studentDto) {
        Student s = studentDto.toStudent();
        studentService.create(s);
        StudentDto result = new StudentDto(s);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/student/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        Optional<Student> os = studentService.findStudentById(id);

        if (os.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        studentService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/student/{id}")
    public ResponseEntity<Void> update(@RequestBody StudentDto studentDto, @PathVariable long id) {
        Optional<Student> os = studentService.findStudentById(id);
        if (os.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Student s = studentDto.toStudent();
        studentService.update(s);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
//componente responsabile degli inidirizzi http (caratteristiche di elaborare dati e una risposta
//determinare le risposte http)6