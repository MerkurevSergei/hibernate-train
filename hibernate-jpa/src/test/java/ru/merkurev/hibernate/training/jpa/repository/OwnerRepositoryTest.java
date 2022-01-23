package ru.merkurev.hibernate.training.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.merkurev.hibernate.training.jpa.entity.Owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OwnerRepositoryTest {

    @Autowired
    OwnerRepository ownerRepository;

    @Test
    void findById() {
        Owner owner = ownerRepository.findById(1L);
        assertNotNull(owner);
    }

    @Test
    @DirtiesContext
    void saveAsPersist() {
        Owner owner = new Owner("Alla");
        ownerRepository.save(owner);
        Owner savedOwner = ownerRepository.findById(owner.getId());
        assertNotNull(savedOwner);
        assertEquals(owner.getName(), savedOwner.getName());
    }

    @Test
    @DirtiesContext
    void testCreationAndUpdateTimestamp() {
        Owner savedOwner = ownerRepository.save(new Owner("Alla"));
        Owner updatedOwner = ownerRepository.save(savedOwner);
        assertNotNull(savedOwner);
        assertNotNull(updatedOwner);
        assertEquals(savedOwner.getCreated(), updatedOwner.getCreated());
        assertNotEquals(savedOwner.getUpdated(), updatedOwner.getUpdated());
    }
}