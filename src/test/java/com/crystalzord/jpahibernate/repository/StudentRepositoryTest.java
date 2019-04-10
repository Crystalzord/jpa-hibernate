package com.crystalzord.jpahibernate.repository;

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
        printStudentAndHisPassport(20001L);
    }


    @Test
    @Transactional
    public void retriveStudentAndPassportDetails() {
        printStudentAndHisPassport(20001L);
    }

    @Test
    @Transactional
    public void retrivePassportAndAssociatedStudent() {
        Passport passport = entityManager.find(Passport.class, 40001L);
        logger.info("Password {} belongs to {}", passport, passport.getStudent());
    }

    private void printStudentAndHisPassport(Long id){
        Student student = entityManager.find(Student.class, id);
        logger.info("Student -> {}", student);
        logger.info("Student -> {}", student.getPassport());
    }
}
