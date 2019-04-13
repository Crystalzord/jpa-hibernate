package com.crystalzord.jpahibernate.repository;

import com.crystalzord.course.entities.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteriaQueriesTest {

    private Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    EntityManager entityManager;

    @Test
    public void criteriaGetAllCourses() {
        //Select c from Course c

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot =criteriaQuery.from(Course.class);

        //3. Define predicates etc using Criteria Builder

        //4. Add predicates etc using Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void criteriaGetAllCoursesHaving100Steps() {
        //Select c from Course c where name like '%100 Steps'

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //3. Define predicates etc using Criteria Builder
        Predicate like100Steps = criteriaBuilder.like(courseRoot.get("name"), "%100 Steps");

        //4. Add predicates etc using Criteria Query
        criteriaQuery.where(like100Steps);

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void criteriaGetAllCoursesWithoutStudents() {
        //Select c from Course c where c.students is empty

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //3. Define predicates etc using Criteria Builder
        Predicate studentsIsEmpty = criteriaBuilder.isEmpty(courseRoot.get("students"));

        //4. Add predicates etc using Criteria Query
        criteriaQuery.where(studentsIsEmpty);

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void join() {
        //Select c from Course c join c.students s

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //3. Define predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students");

        //4. Add predicates etc using Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void join_left() {
        //Select c from Course c left join c.students s

        //1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //3. Define predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        //4. Add predicates etc using Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

}
