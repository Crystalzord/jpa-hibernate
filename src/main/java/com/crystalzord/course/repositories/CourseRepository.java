package com.crystalzord.course.repositories;

import com.crystalzord.course.entities.Course;
import com.crystalzord.course.entities.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public void addHardCodedReviewsForCourse() {
        Course course = findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());

        Review review1 = new Review("5", "Super course!");
        Review review2 = new Review("3", "Good but I miss many topics");

        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        entityManager.persist(review1);
        entityManager.persist(review2);

        logger.info("After adding two reviews course.getReviews() -> {}", course.getReviews());
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("course.getReviews() -> {}", course.getReviews());

        for (Review review : reviews) {
            course.addReview(review);
            review.setCourse(course);
            entityManager.persist(review);
        }

        logger.info("After adding two reviews course.getReviews() -> {}", course.getReviews());
    }

//    public void testEntityManager() {
//        Course course = new Course("Test course");
//        entityManager.persist(course);
//        entityManager.flush();
//
//        course.setName("Test course - updated");
//        entityManager.refresh(course);
//        entityManager.flush();
//
//        Course course2 = findById(10001L);
//        course2.setName("UPDATED UPDATED UPDATED");
//    }

}
