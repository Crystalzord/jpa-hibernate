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
    CourseRepository courseRepository;

    private Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Test
    public void findById_basic() {
        Course course = courseRepository.findById(10001L);
        Assert.assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        courseRepository.deleteById(10002L);
        Assert.assertNull(courseRepository.findById(10002L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        Course course = courseRepository.findById(10001L);
        Assert.assertEquals("JPA in 50 steps", course.getName());
        course.setName("JPA in 50 steps - updated");

        courseRepository.save(course);

        Course course1 = courseRepository.findById(10001L);
        Assert.assertEquals("JPA in 50 steps - updated", course1.getName());
    }

}
