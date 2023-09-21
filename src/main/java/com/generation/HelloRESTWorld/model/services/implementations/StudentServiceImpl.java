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
        var students = repository.findAll();
        return students;
    }

    @Override
    public Optional<Student> findStudentById(long id) {
        var student = repository.findById(id);
        return student;
    }

    @Override
    public Student create(Student s) {
        var student = repository.save(s);
        return student;
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }


    public void update(Student s) {
        repository.save(s);
    }
}
