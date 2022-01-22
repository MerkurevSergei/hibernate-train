package ru.merkurev.hibernate.training.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.entity.Pet;
import ru.merkurev.hibernate.training.jpa.entity.PetOwner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class PetOwnerRepository {

    private final EntityManager em;

    public PetOwner findById(Long id) {
        return em.find(PetOwner.class, id);
    }

    public PetOwner save(PetOwner petOwner) {
        if (petOwner.getId() == null) {
            em.persist(petOwner);
        } else {
            em.merge(petOwner);
        }
        return findById(petOwner.getId());
    }
}
