package ru.merkurev.hibernate.training;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EntitySimplest {
    private Long id;
    private String name;
    
    public EntitySimplest(String name) {
        this.name = name;
    }
}