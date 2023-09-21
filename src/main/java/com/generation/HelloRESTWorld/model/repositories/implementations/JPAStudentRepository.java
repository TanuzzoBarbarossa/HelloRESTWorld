package com.generation.HelloRESTWorld.model.repositories.implementations;

import com.generation.HelloRESTWorld.model.Student;
import com.generation.HelloRESTWorld.model.repositories.abstractions.StudentRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("orm") //repository che devi chiamare da iniettare quando attivo questo profilo
public class JPAStudentRepository implements StudentRepository {
    @PersistenceContext //creazione di un EntityManager e inietta nella variabile entityManager
    private EntityManager entityManager;
    @Override
    public List<Student> findAll() {
        var students = entityManager.createQuery("from Student", Student.class).getResultList();  //jpql (jpa query language)
        return students;
    }

    @Override
    public Optional<Student> findById(long id) {
        return Optional.ofNullable(entityManager.find(Student.class,id));    //.class, oggetto che rappresenta la classe student all'interno della jvm

        //queste righe si possono parafrasare nel metodo di sopra
       /* Student s = entityManager.find(Student.class,id);
        if(s != null){
            return Optional.of(s);
        }
        return Optional.empty();*/
    }

    @Override
    public Student save(Student s) {
        return entityManager.merge(s);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.getReference(Student.class, id));
    }


}
