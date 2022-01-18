package ru.merkurev.hibernate.training.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.merkurev.hibernate.training.jpa.entity.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    void findById() {
        Pet pet = petRepository.findById(2L);
        assertNotNull(pet);
    }

    @Test
    @DirtiesContext
    void deleteById() {
        Pet pet = petRepository.findById(2L);
        petRepository.deleteById(2L);
        Pet petDeleted = petRepository.findById(2L);
        assertNotNull(pet);
        assertNull(petDeleted);
    }

    @Test
    @DirtiesContext
    void saveAsPersist() {
        Pet pet = new Pet("Kitty");
        petRepository.save(pet);
        Pet savedPet = petRepository.findById(pet.getId());
        assertNotNull(savedPet);
        assertEquals(pet.getName(), savedPet.getName());
    }

    @Test
    @DirtiesContext
    void saveAsUpdate() {
        Pet pet = petRepository.findById(2L);
        pet.setName("ChangedName");
        Pet updatedPet = petRepository.save(pet);
        assertEquals(pet.getId(), updatedPet.getId());
        assertEquals(pet.getName(), updatedPet.getName());
    }
}