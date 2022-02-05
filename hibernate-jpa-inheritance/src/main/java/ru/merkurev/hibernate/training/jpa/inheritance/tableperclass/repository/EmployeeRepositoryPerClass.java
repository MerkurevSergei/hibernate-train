package ru.merkurev.hibernate.training.jpa.inheritance.tableperclass.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.inheritance.tableperclass.entity.EmployeePerClass;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class EmployeeRepositoryPerClass {

    private final EntityManager em;

    public EmployeePerClass obtainById(Long id) {
        return em.find(EmployeePerClass.class, id);
    }

    public List<EmployeePerClass> obtainAll() {
        return em.createQuery("select e from EmployeePerClass e", EmployeePerClass.class)
                 .getResultList();
    }

    public EmployeePerClass save(EmployeePerClass employee) {
        if (employee.getId() == null) {
            em.persist(employee);
        } else {
            em.merge(employee);
        }
        return obtainById(employee.getId());
    }
}
