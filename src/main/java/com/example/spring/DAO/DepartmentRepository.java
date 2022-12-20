package com.example.spring.DAO;

import com.example.spring.Model.Department;
import com.example.spring.Model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @EntityGraph(value = "withEmployees",
            type = EntityGraph.EntityGraphType.FETCH)
    Optional<List<Department>> findAllBy(Pageable pageable);

    @EntityGraph(value = "withEmployees",
            type = EntityGraph.EntityGraphType.FETCH)
    Optional<Department> findByName(String name);
}
