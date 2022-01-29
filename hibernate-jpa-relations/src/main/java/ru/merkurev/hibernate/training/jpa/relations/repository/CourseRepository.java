package ru.merkurev.hibernate.training.jpa.relations.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.relations.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class CourseRepository {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private final EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return findById(course.getId());
    }
}