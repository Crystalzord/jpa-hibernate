package com.crystalzord.jpahibernate.repository;

import com.crystalzord.course.entities.Course;
import com.crystalzord.course.entities.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    EntityManager entityManager;

    @Test
    public void jpqlTyped() {
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c -> {}", resultList);
    }

    @Test
    public void jpqlWhere() {
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_50_step_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c where name like '%50 steps' -> {}", resultList);
    }

    @Test
    public void jpqlCoursesWithoutStudents() {
        TypedQuery<Course> query =
                entityManager.createQuery("Select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c where c.students is empty -> {}", resultList);
    }

    @Test
    public void jpqlCoursesWithAtLeast2Students() {
        TypedQuery<Course> query =
                entityManager.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c where c.students >= 2 -> {}", resultList);
    }

    @Test
    public void jpqlCoursesOrderedByStudents() {
        TypedQuery<Course> query =
                entityManager.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c order by size(c.students) desc -> {}", resultList);
    }

    @Test
    public void jpqlStudentsWithPassportInACertainPattern() {
        TypedQuery<Student> query =
                entityManager.createQuery("Select s from Student s where s.passport.number like '%123%'", Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Select s from Student s where s.passport.number like '%123%' -> {}", resultList);
    }

    //JOIN => Select c, s from Course c JOIN c.students s           = If there are courses without students, it will not return them back
    //LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s = It will return even if course has no students
    //CROSS JOIN => Select c, s from Course c, Student s            = Takes every course and mixes with every student present.
    // ^ -> 3 courses, 4 students = 3 * 4 = 12 rows back.

    @Test
    public void joinBasic() {
        Query query = entityManager.createQuery("Select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList) {
            logger.info("Result -> ({}, {})", result[0], result[1]);
        }
    }

    @Test
    public void joinLeft() {
        Query query = entityManager.createQuery("Select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList) {
            logger.info("Result -> ({}, {})", result[0], result[1]);
        }
    }

    @Test
    public void joinCross() {
        Query query = entityManager.createQuery("Select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for(Object[] result:resultList) {
            logger.info("Result -> ({}, {})", result[0], result[1]);
        }
    }
}
