package ru.merkurev.hibernate.training.queries.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    void selectStudentsByLikePattern() {
        TypedQuery<Student> query = 
            em.createQuery("select s from Student s where s.passport.number like '%23-4%'", Student.class);
        List<Student> resultList = query.getResultList();
        assertFalse(resultList.isEmpty());
        assertTrue(resultList.get(0).getPassport().getNumber().contains("23-4"));
    }

    @Test
    @Transactional
    void selectReviewsWithoutRating() {
        TypedQuery<Review> query =
            em.createQuery("select r from Review r where r.rating is null or trim(r.rating) = ''", Review.class);
        List<Review> resultList = query.getResultList();
        assertFalse(resultList.isEmpty());
        String rating = resultList.get(0).getRating();
        assertTrue(rating == null || Objects.equals("", rating.strip()));
    }

    @Test
    @Transactional
    void joinCourseWithStudent() {
        TypedQuery<Object[]> query = em.createQuery("select s, c from Student s JOIN s.courses c", Object[].class);
        List<Object[]> resultList = query.getResultList();
        assertFalse(resultList.isEmpty());
        Object[] result = resultList.get(0);
        assertEquals(result[0].getClass(), Student.class);
        assertEquals(result[1].getClass(), Course.class);
    }
}