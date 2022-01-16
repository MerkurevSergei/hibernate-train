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

    public Person findById(long id) {
        return jdbcTemplate.queryForObject("select * from person where id = ?", new PersonRowMapper(), id);
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from person where id = ?", id);
    }

    public int insert(Person person) {
        return jdbcTemplate.update("insert into person (id, firstName, lastName, birthDate) values (?,?,?,?)",
                                   person.getId(), person.getFirstName(), person.getLastName(), person.getBirthDate());
    }

    public int updateById(Person person) {
        return jdbcTemplate.update("update person set firstName = ?, lastName = ?, birthDate = ? where id = ?",
                                   person.getFirstName(), person.getLastName(), person.getBirthDate(), person.getId());
    }
}