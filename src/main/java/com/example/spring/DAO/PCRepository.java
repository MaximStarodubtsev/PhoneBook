package com.example.spring.DAO;

import com.example.spring.Model.PC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PCRepository extends JpaRepository<PC, Integer> {

    Optional<PC> findByInventorynumber(String inventorynumber);
}
