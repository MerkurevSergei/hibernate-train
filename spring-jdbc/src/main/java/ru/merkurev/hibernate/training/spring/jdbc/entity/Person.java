package ru.merkurev.hibernate.training.spring.jdbc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Person entity.
 *
 * @author MerkurevSergei
 */
@Getter
@Setter
@ToString
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}