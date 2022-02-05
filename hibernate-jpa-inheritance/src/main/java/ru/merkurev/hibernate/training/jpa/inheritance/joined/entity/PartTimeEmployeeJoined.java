package ru.merkurev.hibernate.training.jpa.inheritance.joined.entity;

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
public class PartTimeEmployeeJoined extends EmployeeJoined {

    @Column
    private BigDecimal rate;

    public PartTimeEmployeeJoined(String name, BigDecimal rate) {
        super(name);
        this.rate = rate;
    }
}
