package com.example.spring.Service;

import com.example.spring.DAO.DepartmentRepository;
import com.example.spring.DTO.DepartmentDTO;
import com.example.spring.DTO.MapperDTO;
import com.example.spring.Model.Department;
import jakarta.persistence.UniqueConstraint;
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
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public void delete(Department department) {departmentRepository.delete(department);}

    public List<DepartmentDTO> findPage (Pageable page){
        Optional<List<Department>> departments = departmentRepository.findAllBy(page);
        return departments.map(list->list.stream()
                .map(MapperDTO::departmentDTOMap)
                .collect(Collectors.toList())).orElse(null);
    }

    public Optional<Department> findByName(String name){
        return departmentRepository.findByName(name);
    }

    public void saveAndFlush(Department department){
        departmentRepository.saveAndFlush(department);
    }
}
