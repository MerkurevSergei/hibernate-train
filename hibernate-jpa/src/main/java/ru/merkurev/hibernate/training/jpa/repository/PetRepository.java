package ru.merkurev.hibernate.training.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.entity.Pet;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class PetRepository {

    private final EntityManager em;

    public Pet findById(Long id) {
        return em.find(Pet.class, id);
    }

    public void deleteById(Long id) {
        Pet pet = findById(id);
        em.remove(pet);
    }

    public Pet save(Pet pet) {
        if (pet.getId() == null) {
            em.persist(pet);
        } else {
            em.merge(pet);
        }
        return pet;
    }
}
