package ru.merkurev.hibernate.training.jpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "owner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedQueries({@NamedQuery(name = "get_all_owners", query = "select o from Owner o"),
    @NamedQuery(name = "get_owner_by_id", query = "select o from Owner o where o.id = :id")})
public class Owner {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    public Owner(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Owner owner = (Owner) o;
        return id != null && Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
