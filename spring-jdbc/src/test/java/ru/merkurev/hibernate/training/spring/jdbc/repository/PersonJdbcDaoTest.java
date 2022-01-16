package ru.merkurev.hibernate.training.spring.jdbc.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.merkurev.hibernate.training.spring.jdbc.entity.Person;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PersonJdbcDaoTest {

    @Autowired
    private PersonJdbcDao personJdbcDao;

    @Test
    @DisplayName("Получить список Person")
    void findAll() {
        List<Person> persons = personJdbcDao.findAll();
        assertFalse(persons.isEmpty());
    }

    @Test
    @DisplayName("Получить Person по идентификатору")
    void findById() {
        Person person = personJdbcDao.findById(1L);
        assertNotNull(person);
    }

    @Test
    @DisplayName("Удалить Person по идентификатору")
    void deleteById() {
        int count = personJdbcDao.deleteById(2L);
        assertEquals(1, count);
    }

    @Test
    @DisplayName("Обновить Person по идентификатору")
    void updateById() {
        Person expected = new Person(3, "Three Name", "Three Family", LocalDate.now());
        int count = personJdbcDao.updateById(expected);
        Person actual = personJdbcDao.findById(3L);
        assertEquals(1, count);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getBirthDate(), actual.getBirthDate());
    }

    @Test
    @DisplayName("Создать Person")
    void insert() {
        Person expected = new Person(4L, "Four Name", "Four Family", LocalDate.now());
        int count = personJdbcDao.insert(expected);
        Person actual = personJdbcDao.findById(4L);
        assertEquals(1, count);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getBirthDate(), actual.getBirthDate());
    }
}