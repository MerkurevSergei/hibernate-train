package ru.merkurev.hibernate.training;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HibernateJPATest {
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        // like discussed with regards to SessionFactory, an EntityManagerFactory is set up once for an application
        // 		IMPORTANT: notice how the name here matches the name we gave the persistence-unit in persistence.xml!
        entityManagerFactory = Persistence.createEntityManagerFactory("ru.merkurev.hibernate.training" );
    }

    @AfterEach
    protected void tearDown() throws Exception {
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("JPA Provider")
    public void testBasicUsage() {
        // create a couple of entity...
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new EntityAnnotated("First Name"));
        entityManager.persist(new EntityAnnotated("Second Name"));
        entityManager.getTransaction().commit();
        entityManager.close();

        // now lets pull entity from the database and list them
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<EntityAnnotated> result = entityManager.createQuery("from EntityAnnotated", EntityAnnotated.class )
                                                    .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        assertEquals(2, result.size());
    }
}