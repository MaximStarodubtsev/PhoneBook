package com.example.spring.Service;

import com.example.spring.DAO.RoleRepository;
import com.example.spring.Model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> findByName(String name){
        return roleRepository.findByName(name);
    }

    public void saveAndFlush(Role role){
        roleRepository.saveAndFlush(role);
    }
}
