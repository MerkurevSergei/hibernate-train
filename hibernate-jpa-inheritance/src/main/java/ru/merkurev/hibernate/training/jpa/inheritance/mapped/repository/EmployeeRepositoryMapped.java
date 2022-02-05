package ru.merkurev.hibernate.training.jpa.inheritance.mapped.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.inheritance.mapped.entity.EmployeeMapped;
import ru.merkurev.hibernate.training.jpa.inheritance.mapped.entity.FullTimeEmployeeMapped;
import ru.merkurev.hibernate.training.jpa.inheritance.mapped.entity.PartTimeEmployeeMapped;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class EmployeeRepositoryMapped {

    private final EntityManager em;

    public List<FullTimeEmployeeMapped> obtainAllFullTimeEmployee() {
        return em.createQuery("select e from FullTimeEmployeeMapped e", FullTimeEmployeeMapped.class)
                 .getResultList();
    }

    public List<PartTimeEmployeeMapped> obtainAllPartTimeEmployee() {
        return em.createQuery("select e from PartTimeEmployeeMapped e", PartTimeEmployeeMapped.class)
                 .getResultList();
    }

    public void save(EmployeeMapped employee) {
        if (employee.getId() == null) {
            em.persist(employee);
        } else {
            em.merge(employee);
        }
    }
}
