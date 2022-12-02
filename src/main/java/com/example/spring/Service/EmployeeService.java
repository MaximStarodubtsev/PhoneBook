package com.example.spring.Service;

import com.example.spring.DAO.EmployeeRepository;
import com.example.spring.Model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Optional<Employee> findByPhone(String phoneNumber){
        return employeeRepository.findByPhonenumber(phoneNumber);
    }

    public void saveOrUpdate(Employee employee){
        employeeRepository.saveAndFlush(employee);
    }

}
