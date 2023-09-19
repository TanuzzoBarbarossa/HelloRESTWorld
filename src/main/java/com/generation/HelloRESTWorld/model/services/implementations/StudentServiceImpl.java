package com.generation.HelloRESTWorld.model.services.implementations;

import com.generation.HelloRESTWorld.model.Student;
import com.generation.HelloRESTWorld.model.repositories.abstractions.StudentRepository;
import com.generation.HelloRESTWorld.model.services.abstractions.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service    //deve venir inettato quando   qualcuno chiama studentServiceImpl
public class StudentServiceImpl implements StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository){
        this.repository = repository;
    }
    @Override
    public Iterable<Student> getAllStudents() {
        var students = repository.getAllStudents();
        return students;
    }

    @Override
    public Optional<Student> findStudentById(long id) {
        var student = repository.findStudentById(id);
        return student;
    }

    @Override
    public Student create(Student s) {
        var student = repository.create(s);
        return student;
    }
}
