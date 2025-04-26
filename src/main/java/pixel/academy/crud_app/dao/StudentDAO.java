package pixel.academy.crud_app.dao;

import pixel.academy.crud_app.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save (Student theStudent);

    Student findbyId(Integer id);

    List<Student> findAll ();

    List<Student> findByLastName(String theLastName);

    void update (Student theStudent);

    void delete (Integer id);

    int deleteAll();



}
