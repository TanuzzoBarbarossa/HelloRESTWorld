package com.generation.HelloRESTWorld.model.repositories.abstractions;

import com.generation.HelloRESTWorld.model.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//farà spring questa classe (diciamo a spring di crearla dietro le quinte mentre il servizio web è in moto)
@Repository
@Profile("spring")
//interfaccia non presenta metodi perché le estende da JpaRepository (spring)
public interface SpringJPAStudentRepository extends StudentRepository, JpaRepository<Student, Long> { }
