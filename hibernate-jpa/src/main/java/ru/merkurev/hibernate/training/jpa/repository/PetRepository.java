package ru.merkurev.hibernate.training.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.entity.Pet;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PetRepository {
    private final EntityManager em;

    public Pet findById(Long id) {
        return em.find(Pet.class, id);
    }

    public void deleteById(Long id) {
        em.remove(id);
    }

    public Pet save(Long id) {
        Pet pet = findById(id);
        return em.merge(pet);
    }
}
