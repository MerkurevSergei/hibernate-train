package ru.merkurev.hibernate.training.jpa.relations.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.relations.entity.Passport;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class PassportRepository {
    private final EntityManager em;

    public Passport findById(Long id) {
        return em.find(Passport.class, id);
    }

    public Passport save(Passport passport) {
        if (passport.getId() == null) {
            em.persist(passport);
        } else {
            em.merge(passport);
        }
        return findById(passport.getId());
    }
}