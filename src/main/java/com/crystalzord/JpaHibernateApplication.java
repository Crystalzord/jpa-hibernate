package com.crystalzord;

import com.crystalzord.entity.Course;
import com.crystalzord.entity.Review;
import com.crystalzord.entity.Student;
import com.crystalzord.repository.CourseRepository;
import com.crystalzord.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		List<Review> reviews = new ArrayList<>();
//		Review review1 = new Review("5", "Super course!");
//		Review review2 = new Review("3", "Good but I miss many topics");
//		reviews.add(review1);
//		reviews.add(review2);
//
//		courseRepository.addReviewsForCourse(10003L, reviews);

//		studentRepository.addHardCodedStudentAndCourse();

		Student student = new Student("New Student");
		Course course = new Course("New in 900 steps");
		studentRepository.addStudentAndCourse(student, course);
	}
}
