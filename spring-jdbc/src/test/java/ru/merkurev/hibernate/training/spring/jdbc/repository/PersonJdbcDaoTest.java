package ru.merkurev.hibernate.training.spring.jdbc.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.merkurev.hibernate.training.spring.jdbc.entity.Person;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class PersonJdbcDaoTest {

    @Autowired
    private PersonJdbcDao personJdbcDao;
    

    @Test
    void findAll() {
        List<Person> all = personJdbcDao.findAll();
        System.out.println(all);
    }
}