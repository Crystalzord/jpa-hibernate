package com.crystalzord.jpahibernate.repository;

import com.crystalzord.entity.Course;
import com.crystalzord.entity.Passport;
import com.crystalzord.entity.Student;
import com.crystalzord.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(StudentRepositoryTest.class);

    @Test
    @Transactional
    @DirtiesContext
    public void someTest() {
        studentRepository.someOperationToUnderstandPersistenceContext();
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("Passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    public void retriveStudentAndPassportDetails() {
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("Passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    public void retrivePassportAndAssociatedStudent() {
        Passport passport = entityManager.find(Passport.class, 40001L);
        logger.info("Passport {} belongs to {}", passport, passport.getStudent());
    }

    @Test
    @Transactional
    public void retriveStudentAndCourses() {
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("Courses -> {}", student.getCourses());
    }

    @Test
    @Transactional
    public void retriveCourseAndStudents() {
        Course course = entityManager.find(Course.class, 10001L);
        logger.info("Course -> {}", course);
        logger.info("Students -> {}", course.getStudents());
    }
}
