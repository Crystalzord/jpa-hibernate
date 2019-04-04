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
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueriesTest {

    @Autowired
    EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Test
    public void nativeQueriesBasic() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE", Course.class);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE -> {}", resultList);
    }

    @Test
    public void nativeQueriesWithParameters() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE id = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE WHERE id = ? -> {}", resultList);
    }

    @Test
    public void nativeQueriesWithNamedParameters() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE id = :id", Course.class);
        query.setParameter("id", 10001L);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE WHERE id = :id -> {}", resultList);
    }

    @Test
    @Transactional
    public void nativeQueriesUpdate() {
        Query query = entityManager.createNativeQuery("UPDATE COURSE SET last_updated_date=sysdate() WHERE id = ?", Course.class);
        query.setParameter(1, 10001L);
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
    }

}
