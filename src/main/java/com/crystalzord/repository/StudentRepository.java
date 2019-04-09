package com.crystalzord.repository;

import com.crystalzord.entity.Passport;
import com.crystalzord.entity.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

    @PersistenceContext
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
        return student;
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        entityManager.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        entityManager.persist(student);
    }

    public void testEntityManager() {
        Student student = new Student("Test student");
        entityManager.persist(student);
        entityManager.flush();

        student.setName("Test student - updated");
        entityManager.refresh(student);
        entityManager.flush();

        Student student2 = findById(10001L);
        student2.setName("UPDATED UPDATED UPDATED");
    }

}
