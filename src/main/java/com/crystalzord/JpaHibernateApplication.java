package com.crystalzord;

import com.crystalzord.hierarchies.entities.FullTimeEmployee;
import com.crystalzord.hierarchies.entities.PartTimeEmployee;
import com.crystalzord.hierarchies.repositories.EmployeeRepository;
import com.crystalzord.course.repositories.CourseRepository;
import com.crystalzord.course.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;


	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		employeeRepository.insert(new PartTimeEmployee("Mike", new BigDecimal(50)));
//		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal(10000)));
//
//		logger.info("Full Time Employees -> {}", employeeRepository.retriveAllFullTimeEmployees());
//		logger.info("Part Time Employees -> {}", employeeRepository.retriveAllPartTimeEmployees());
	}
}
