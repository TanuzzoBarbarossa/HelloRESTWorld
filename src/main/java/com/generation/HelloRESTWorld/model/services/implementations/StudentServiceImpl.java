package com.generation.HelloRESTWorld.model.services.implementations;

import com.generation.HelloRESTWorld.model.Student;
import com.generation.HelloRESTWorld.model.services.abstractions.StudentService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service    //deve venir inettato quando   qualcuno chiama studentServiceImpl
public class StudentServiceImpl implements StudentService {
    @Override
    public Iterable<Student> getAllStudents() {
        return null;
    }

    @Override
    public Optional<Student> findStudentById(long id) {
        return Optional.empty();
    }

    @Override
    public Student create(Student s) {
        return null;
    }
}
