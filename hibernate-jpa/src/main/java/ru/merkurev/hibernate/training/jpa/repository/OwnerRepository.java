package ru.merkurev.hibernate.training.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.entity.Owner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class OwnerRepository {

    private final EntityManager em;

    public Owner findById(Long id) {
        return em.find(Owner.class, id);
    }

    public Owner save(Owner owner) {
        if (owner.getId() == null) {
            em.persist(owner);
        } else {
            em.merge(owner);
        }
        return findById(owner.getId());
    }
}
