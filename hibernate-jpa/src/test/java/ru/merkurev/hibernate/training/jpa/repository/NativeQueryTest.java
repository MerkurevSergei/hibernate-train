package ru.merkurev.hibernate.training.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.merkurev.hibernate.training.jpa.entity.Owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NativeQueryTest {

    @Autowired
    OwnerRepository ownerRepository;

    @Test
    void nativeQueryFindOne() {
        Owner owner = ownerRepository.getOwnerAsNativeQuery(1L);
        assertNotNull(owner);
        assertEquals(1L, owner.getId());
    }
}