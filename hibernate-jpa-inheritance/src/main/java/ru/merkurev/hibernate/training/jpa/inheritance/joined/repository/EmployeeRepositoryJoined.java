package ru.merkurev.hibernate.training.jpa.inheritance.joined.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.inheritance.joined.entity.EmployeeJoined;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class EmployeeRepositoryJoined {

    private final EntityManager em;

    public EmployeeJoined obtainById(Long id) {
        return em.find(EmployeeJoined.class, id);
    }

    public List<EmployeeJoined> obtainAll() {
        return em.createQuery("select e from EmployeeJoined e", EmployeeJoined.class)
                 .getResultList();
    }

    public EmployeeJoined save(
        EmployeeJoined employee) {
        if (employee.getId() == null) {
            em.persist(employee);
        } else {
            em.merge(employee);
        }
        return obtainById(employee.getId());
    }
}
