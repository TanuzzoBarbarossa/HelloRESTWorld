package com.generation.HelloRESTWorld.model.services.abstractions;

import com.generation.HelloRESTWorld.model.Student;

import java.util.Optional;

public interface StudentService {
        Iterable<Student> getAllStudents();

        Optional<Student> findStudentById(long id);

        Student create(Student s);
}
