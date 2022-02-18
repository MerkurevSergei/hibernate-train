package ru.merkurev.hibernate.training.queries.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CriteriaQueriesTests {


    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void criteriaBasic() {
        // Select c from Course c

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> root = cq.from(Course.class);

        // 3. Define Predicates etc using CriteriaBuilder

        // 4. Add Predicates etc to Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(root));

        List<Course> resultList = query.getResultList();
        assertFalse(resultList.isEmpty());
    }

    @Test
    @Transactional
    void criteriaLikeMega() {
        // Select c from Course c where c.name like "%mega%"

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> root = cq.from(Course.class);

        // 3. Define Predicates etc using CriteriaBuilder
        Predicate like100Steps = cb.like(root.get("name"), "%mega%");

        // 4. Add Predicates etc to Criteria Query
        cq.where(like100Steps);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(root));

        List<Course> resultList = query.getResultList();
        assertEquals(1, resultList.size());
    }

    @Test
    @Transactional
    void criteriaIsEmpty() {
        // Select c from Course c where c.students is empty"

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> root = cq.from(Course.class);

        // 3. Define Predicates etc using CriteriaBuilder
        Predicate studentsIsEmpty = cb.isEmpty(root.get("students"));

        // 4. Add Predicates etc to Criteria Query
        cq.where(studentsIsEmpty);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(root));

        List<Course> resultList = query.getResultList();
        assertEquals(1, resultList.size());
    }
}