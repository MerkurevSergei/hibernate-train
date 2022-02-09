package ru.merkurev.hibernate.training.queries.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JPQLTests {


    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void saveEmployee() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        assertFalse(resultList.isEmpty());
        assertTrue(resultList.get(0).getStudents().isEmpty());
    }

}