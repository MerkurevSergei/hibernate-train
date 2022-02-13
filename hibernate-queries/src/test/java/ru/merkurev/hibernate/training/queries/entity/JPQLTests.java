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
    void selectCoursesWithEmptyStudents() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        assertFalse(resultList.isEmpty());
        assertTrue(resultList.get(0).getStudents().isEmpty());
    }

    @Test
    @Transactional
    void selectCoursesWithSomeStudents() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> resultList = query.getResultList();
        assertFalse(resultList.isEmpty());
        assertFalse(resultList.get(0).getStudents().isEmpty());
    }

    @Test
    @Transactional
    void selectCoursesOrderedByStudentsCount() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students)", Course.class);
        List<Course> resultList = query.getResultList();
        assertTrue(resultList.size() > 1);
        assertTrue(resultList.get(0).getStudents().size() < resultList.get(1).getStudents().size());
    }

    @Test
    @Transactional
    void selectCoursesByLikePattern() {
        TypedQuery<Student> query = 
            em.createQuery("select s from Student s where s.passport.number like '%23-4%'", Student.class);
        List<Student> resultList = query.getResultList();
        assertFalse(resultList.isEmpty());
        assertTrue(resultList.get(0).getPassport().getNumber().contains("23-4"));
    }
}