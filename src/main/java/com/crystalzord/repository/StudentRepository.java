package com.crystalzord.repository;

import com.crystalzord.entity.Course;
import com.crystalzord.entity.Passport;
import com.crystalzord.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public void someOperationToUnderstandPersistenceContext() {
        Student student = entityManager.find(Student.class, 20001L);
        Passport passport = student.getPassport();
        passport.setNumber("G241 - Updated");
        student.setName("Lukasz - Updated");
    }

    public void addHardCodedStudentAndCourse() {
        Student student = new Student("Jack");
        Course course = new Course("Microservices in 100 Steps");

        entityManager.persist(student);
        entityManager.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);
    }

    public void addStudentAndCourse(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);
        entityManager.persist(student);
        entityManager.persist(course);
    }

}
