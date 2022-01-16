package ru.merkurev.hibernate.training.spring.jdbc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.spring.jdbc.entity.Person;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonJdbcDao {
    private final JdbcTemplate jdbcTemplate;
    
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }
}
