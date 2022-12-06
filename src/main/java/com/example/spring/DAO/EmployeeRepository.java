package com.example.spring.DAO;

import com.example.spring.Model.Employee;
import com.example.spring.Model.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Optional<List<Employee>> findAllBy(Pageable pageable);

    List<Employee> findAllByRoles(Role role);

    void deleteByPhonenumber(String phoneNum);
    Optional<Employee> findByPhonenumber(String phoneNumber);
}
