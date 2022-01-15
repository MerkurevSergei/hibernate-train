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

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HibernateAnnotationTest {


    private SessionFactory sessionFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(HibernateAnnotationTest.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        Configuration cfg = new Configuration().setProperties(properties)
                                               .configure();
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .applySettings(cfg.getProperties())
            .build();
        try {
            this.sessionFactory = new MetadataSources(registry).addAnnotatedClass(EntityAnnotated.class)
                                                               .buildMetadata()
                                                               .buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @AfterEach
    protected void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    @DisplayName("Конфигурация через properties файл и настройку произвольных свойств")
    public void test() {
        // create a couple of entities...
        EntityAnnotated first = new EntityAnnotated("First Name");
        EntityAnnotated second = new EntityAnnotated("Second Name");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(first);
            session.save(second);
            session.getTransaction().commit();
        }

        // now lets pull entities from the database and list them
        List<EntityAnnotated> result;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = (List<EntityAnnotated>) session.createQuery("from EntityAnnotated").list();
            session.getTransaction().commit();
            session.close();
        }
        assertEquals(2, result.size());
    }
}