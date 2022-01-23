package ru.merkurev.hibernate.training.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.merkurev.hibernate.training.jpa.entity.Owner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NamedQueryTest {

    @Autowired
    OwnerRepository ownerRepository;

    @Test
    void namedQueriesFindAll() {
        List<Owner> owners = ownerRepository.getOwnersAsNamedQuery();
        assertFalse(owners.isEmpty());
    }

    @Test
    void namedQueriesFindOne() {
        Owner owner = ownerRepository.getOwnerAsNamedQuery(1L);
        assertNotNull(owner);
    }
}