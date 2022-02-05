package ru.merkurev.hibernate.training.jpa.inheritance.singletable.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FullTimeEmployee extends Employee {

    @Column
    private BigDecimal salary;

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }
}
