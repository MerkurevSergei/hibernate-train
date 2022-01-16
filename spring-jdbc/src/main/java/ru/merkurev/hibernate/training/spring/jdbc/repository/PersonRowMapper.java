package ru.merkurev.hibernate.training.spring.jdbc.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.merkurev.hibernate.training.spring.jdbc.entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(rs.getLong("id"),
                          rs.getString("firstName"),
                          rs.getString("lastName"),
                          rs.getDate("birthDate").toLocalDate());
    }
}