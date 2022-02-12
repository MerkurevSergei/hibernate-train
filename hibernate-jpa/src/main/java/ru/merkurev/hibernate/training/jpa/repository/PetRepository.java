package ru.merkurev.hibernate.training.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.entity.Pet;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
        return findById(pet.getId());
    }

    public Pet save2(Pet pet) {
        em.persist(pet);
        return findById(pet.getId());
    }
    
    public Pet saveAndChange(Pet pet, String newName) {
        if (pet.getId() == null) {
            em.persist(pet);
        } else {
            throw new RuntimeException("Unsupported operation with null Pet");
        }
        pet.setName(newName);
        return findById(pet.getId());
    }

    public Pet saveDetachAndChange(Pet pet, String newName) {
        if (pet.getId() == null) {
            em.persist(pet);
        } else {
            throw new RuntimeException("Unsupported operation with null Pet");
        }
        em.detach(pet);
        pet.setName(newName);
        return findById(pet.getId());
    }

    public Pet saveClearAndChange(Pet pet, String newName) {
        if (pet.getId() == null) {
            em.persist(pet);
        } else {
            throw new RuntimeException("Unsupported operation with null Pet");
        }
        em.clear();
        pet.setName(newName);
        return findById(pet.getId());
    }

    public Pet saveFlushChangeDetach(Pet pet, String newName) {
        if (pet.getId() == null) {
            em.persist(pet);
        } else {
            throw new RuntimeException("Unsupported operation with null Pet");
        }
        em.flush();
        pet.setName(newName);
        em.detach(pet);
        return findById(pet.getId());
    }

    public Pet saveFlushChangeClear(Pet pet, String newName) {
        if (pet.getId() == null) {
            em.persist(pet);
        } else {
            throw new RuntimeException("Unsupported operation with null Pet");
        }
        em.flush();
        pet.setName(newName);
        em.clear();
        return findById(pet.getId());
    }

    public Pet saveDetachRefreshChange(Pet pet, String newName) {
        if (pet.getId() == null) {
            em.persist(pet);
        } else {
            throw new RuntimeException("Unsupported operation with null Pet");
        }
        pet.setName(newName);
        em.refresh(pet);
        return findById(pet.getId());
    }

    public Pet saveFlushChangeRefresh(Pet pet, String newName) {
        if (pet.getId() == null) {
            em.persist(pet);
        } else {
            throw new RuntimeException("Unsupported operation with null Pet");
        }
        em.flush();
        pet.setName(newName);
        em.refresh(pet);
        return findById(pet.getId());
    }
}
