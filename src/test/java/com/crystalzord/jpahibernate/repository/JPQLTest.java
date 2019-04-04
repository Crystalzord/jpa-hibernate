package com.crystalzord.jpahibernate.repository;

import com.crystalzord.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest {

    @Autowired
    EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);

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

}
