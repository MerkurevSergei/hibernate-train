package ru.merkurev.hibernate.training.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.merkurev.hibernate.training.jpa.entity.Pet;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class JPQLTest {

    @Autowired
    private EntityManager em;

    @Test
    void findAll() {
        Query allPetsQuery = em.createQuery("Select p from Pet p");
        List resultList = allPetsQuery.getResultList();
        assertFalse(resultList.isEmpty());
    }

    @Test
    void findAllTyped() {
        TypedQuery<Pet> allPetsQuery = em.createQuery("Select p from Pet p", Pet.class);
        List<Pet> resultList = allPetsQuery.getResultList();
        assertFalse(resultList.isEmpty());
    }

    @Test
    void findWhere() {
        TypedQuery<Pet> pets_query = em.createQuery("Select p from Pet p where p.id  = :id", Pet.class);
        pets_query.setParameter("id", 1L);
        List<Pet> resultList = pets_query.getResultList();
        assertFalse(resultList.isEmpty());
    }
}