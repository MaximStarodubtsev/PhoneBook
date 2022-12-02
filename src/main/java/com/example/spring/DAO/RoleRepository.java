package com.example.spring.DAO;

import com.example.spring.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Optional<Role> findByName(String name);
}
