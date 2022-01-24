package ru.merkurev.hibernate.training.jpa.relations.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.relations.entity.Student;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class StudentRepository {
    private final EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }
}