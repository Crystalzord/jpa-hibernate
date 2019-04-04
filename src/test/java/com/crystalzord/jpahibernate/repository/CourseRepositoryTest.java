package com.crystalzord.jpahibernate.repository;

import com.crystalzord.entity.Course;
import com.crystalzord.repository.CourseRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;

    private Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Test
    public void findById_basic() {
        Course course = repository.findById(10001L);
        Assert.assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        repository.deleteById(10002L);
        Assert.assertNull(repository.findById(10002L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        Course course = repository.findById(10001L);
        Assert.assertEquals("JPA in 50 steps", course.getName());
        course.setName("JPA in 50 steps - updated");

        repository.save(course);

        Course course1 = repository.findById(10001L);
        Assert.assertEquals("JPA in 50 steps - updated", course1.getName());
    }

}
