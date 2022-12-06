package com.example.spring.Service;

import com.example.spring.DAO.RoleRepository;
import com.example.spring.DTO.MapperDTO;
import com.example.spring.DTO.RoleDTO;
import com.example.spring.Model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public  void delete(String name){
        roleRepository.deleteByName(name);
    }
    public List<RoleDTO> findPage (Pageable pageable){
        Optional<List<Role>> roles =roleRepository.findAllBy(pageable);
        return roles.map(list->list.stream()
                .map(MapperDTO::roleDTOMap)
                .collect(Collectors.toList())).orElse(null);
    }

}
