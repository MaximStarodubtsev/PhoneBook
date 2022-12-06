package com.example.spring.DAO;

import com.example.spring.Model.Department;
import com.example.spring.Model.Employee;
import com.example.spring.Model.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    void deleteByName(String name);
    Optional<List<Role>> findAllBy(Pageable pageable);
}
