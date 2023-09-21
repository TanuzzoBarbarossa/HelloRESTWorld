package com.generation.HelloRESTWorld.model.repositories.implementations;

import com.generation.HelloRESTWorld.model.Student;
import com.generation.HelloRESTWorld.model.repositories.abstractions.StudentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class JDBCStudentRepository implements StudentRepository {
    public final static String JDBC_URL = "jdbc:mysql://:3306/db_example";
    public final static String JDBC_USER = "root";
    public final static String JDBC_PASSWORD = "Super@2003";
    public final static String ALL_STUDENTS = "select id,firstname,lastname,birthdate from students";
    public final static String FIND_STUDENT_BY_ID = "select firstname,lastname,birthdate from students where id =?";
    public final static String CREATE_STUDENT = "insert into students (firstname,lastname,birthdate) values (?,?,?)";
    public final static String DELETE_STUDENT = "delete from students where id =?";

    public final static String UPDATE_STUDENT = "update students set firstname =?, lastname =? where id =?";

    public List<Student> findAll() {
        List<Student> students = new ArrayList<Student>();

        try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(ALL_STUDENTS);
        ) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDate birthdate = rs.getDate("birthdate").toLocalDate();

                Student student = new Student(id, firstname, lastname, birthdate);

                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Student> findById(long id) {

        try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement st = con.prepareStatement(FIND_STUDENT_BY_ID);
        ) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
                    Student student = new Student(id, firstname, lastname, birthdate);
                    return Optional.of(student);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student save(Student s) {
        if(s.getId() == 0){
            return create(s);
        }
        try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement st = con.prepareStatement(UPDATE_STUDENT);
        ) {
            st.setString(1, s.getFirstname());
            st.setString(2, s.getLastname());
            st.executeUpdate();

            return s;
            }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

        private Student create(Student s){
            try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                 PreparedStatement st = con.prepareStatement(CREATE_STUDENT, Statement.RETURN_GENERATED_KEYS);
            ) {
                st.setString(1, s.getFirstname());
                st.setString(2, s.getLastname());
                st.setDate(3, Date.valueOf(s.getBirthdate()));
                st.executeUpdate();
                try(ResultSet rs = st.getGeneratedKeys()){
                    if(rs.next()){
                        long id = rs.getLong(1);
                        s.setId(id);
                    }
                }
                return s;
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public void deleteById(Long id) {
       /* try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement st = con.prepareStatement(DELETE_STUDENT);
        ) {
            st.setLong(1, id);
            st.executeUpdate();
            try(ResultSet rs = st.getGeneratedKeys()){
                if(rs.next()){
                    long id = rs.getLong(1);
                    s.setId(id);
                }
            }
            return s;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }


}
