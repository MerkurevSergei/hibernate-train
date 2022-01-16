package ru.merkurev.hibernate.training.spring.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}