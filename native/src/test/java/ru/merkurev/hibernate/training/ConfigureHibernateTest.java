package ru.merkurev.hibernate.training;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConfigureHibernateTest {

    private SessionFactory sessionFactory;

    @Test
    @DisplayName("Стандартная конфигурация hibernate, xml файл перезаписывает properties")
    public void standardFileConfiguration() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml or hibernate.properties
            .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        try (Session session = sessionFactory.openSession()) {
            assertNotNull(session);
        }
    }

    @Test
    @DisplayName("Конфигурация hibernate через xml файл конфигурации")
    public void customXmlFileConfiguration() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml")
            .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        try (Session session = sessionFactory.openSession()) {
            assertNotNull(session);
        }
    }

    @Test
    @DisplayName("Конфигурация hibernate через properties файл и настройку произвольных свойств")
    public void customPropertiesFileConfiguration() {
        Properties properties = new Properties();
        StandardServiceRegistry registry = null;
        try {
            properties.load(ConfigureHibernateTest.class.getClassLoader().getResourceAsStream("hibernate.properties"));
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            Configuration cfg = new Configuration().setProperties(properties).configure();
            registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Throwable e) {
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        try (Session session = sessionFactory.openSession()) {
            assertNotNull(session);
        }
    }
}