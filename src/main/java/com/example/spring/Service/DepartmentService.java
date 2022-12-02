package com.example.spring.Service;

import com.example.spring.DAO.DepartmentRepository;
import com.example.spring.Model.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Optional<Department> findByName(String name){
        return departmentRepository.findByName(name);
    }

    public void saveAndFlush(Department department){
        departmentRepository.saveAndFlush(department);
    }
}
