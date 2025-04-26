package pixel.academy.crud_app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.From;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pixel.academy.crud_app.entity.Student;

import java.util.List;

@Repository
public class StudentDAOImplementation implements StudentDAO {

    // field for EntityManager (used for interaction with the database)
    private EntityManager entityManager;

    // inject EntityManager through constructor (recommended practice for testability and modularity)
    @Autowired
    public StudentDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implementation of the save method for saving a Student object to the database
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findbyId(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {

        // create Query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // create Query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);

        // set parameters for the query
        theQuery.setParameter("theData", theLastName);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {

        entityManager.merge(theStudent);

    }

    @Override
    @Transactional
    public void delete(Integer id) {

        // preluam studentul di baza de date
        Student theStudent = entityManager.find(Student.class, id);

        // stergem studentul
        entityManager.remove(theStudent);

    }

    @Override
    @Transactional
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return 0;
    }

}
