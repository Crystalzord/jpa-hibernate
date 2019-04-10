package com.crystalzord.repository;

import com.crystalzord.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

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
