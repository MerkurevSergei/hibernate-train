package ru.merkurev.hibernate.training;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HibernateXmlConfigTest {
    
    private SessionFactory sessionFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.properties or hibernate.cfg.xml (override properties)
            // .configure("hibernate.cfg.xml") // or set custom xml settings file 
            .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @AfterEach
    protected void tearDown() throws Exception {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    @DisplayName("Стандартная конфигурация, отображение через mapping file")
    public void test() {
        // create a couple of entities...
        EntitySimplest first = new EntitySimplest("First Name");
        EntitySimplest second = new EntitySimplest("Second Name");
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(first);
            session.save(second);
            session.getTransaction().commit();
        }

        // now lets pull entities from the database and list them
        List<EntitySimplest> result;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result =(List<EntitySimplest>) session.createQuery("from EntitySimplest").list();
            session.getTransaction().commit();
            session.close();
        }
        assertEquals(2, result.size());
    }
}