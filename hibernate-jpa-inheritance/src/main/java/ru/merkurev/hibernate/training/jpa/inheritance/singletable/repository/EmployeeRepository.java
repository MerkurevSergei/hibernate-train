package ru.merkurev.hibernate.training.jpa.inheritance.singletable.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.merkurev.hibernate.training.jpa.inheritance.singletable.entity.Employee;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class EmployeeRepository {

    private final EntityManager em;

    public Employee obtainById(Long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> obtainAll() {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            em.persist(employee);
        } else {
            em.merge(employee);
        }
        return obtainById(employee.getId());
    }
}
