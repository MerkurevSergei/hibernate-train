package ru.merkurev.hibernate.training.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.merkurev.hibernate.training.jpa.entity.Pet;
import ru.merkurev.hibernate.training.jpa.entity.PetOwner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PetOwnerRepositoryTest {

    @Autowired
    PetOwnerRepository petOwnerRepository;

    @Test
    void findById() {
        PetOwner owner = petOwnerRepository.findById(1L);
        assertNotNull(owner);
    }

    @Test
    @DirtiesContext
    void saveAsPersist() {
        PetOwner owner = new PetOwner("Kitty");
        petOwnerRepository.save(owner);
        PetOwner savedOwner = petOwnerRepository.findById(owner.getId());
        assertNotNull(savedOwner);
        assertEquals(owner.getName(), savedOwner.getName());
    }

}