package ru.merkurev.hibernate.training;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENTITIES")
@Getter
@Setter
@NoArgsConstructor
public class EntityAnnotated {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String name;

    public EntityAnnotated(String name) {
        this.name = name;
    }
}
