package com.example.spring.DAO;

import com.example.spring.Model.Employee;
import com.example.spring.Model.Role;
import jakarta.persistence.Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            value = "withDepartmentAndPCAndRoles")
    Optional<List<Employee>> findAllBy(Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            value = "withDepartmentAndPCAndRoles")
    List<Employee> findAllByRoles(Role role);

    void deleteByPhonenumber(String phoneNum);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            value = "withDepartmentAndPCAndRoles")
    Optional<Employee> findByPhonenumber(String phoneNumber);
}
