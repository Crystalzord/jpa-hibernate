package com.crystalzord.jpahibernate.repository;

import com.crystalzord.course.entities.Course;
import com.crystalzord.course.entities.Review;
import com.crystalzord.course.repositories.CourseRepository;
import org.junit.Assert;
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
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager entityManager;

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

    @Test
    @Transactional
    public void retriveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    public void retriveCourseForReview() {
        Review review = entityManager.find(Review.class, 50001L);
        logger.info("{}", review.getCourse());
    }

}
