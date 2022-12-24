package com.example.spring.Service;

import com.example.spring.DAO.EmployeeRepository;
import com.example.spring.DTO.EmployeeDTO;
import com.example.spring.DTO.MapperDTO;
import com.example.spring.Model.Employee;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<EmployeeDTO> findPage(Pageable page){
        Optional<List<Employee>> employees = employeeRepository.findAllBy(page);
        return employees.map(list -> list.stream()
                .map(MapperDTO::employeeDTOMap)
                .collect(Collectors.toList())).orElse(null);
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findByPhone(String phoneNumber){
        Optional<Employee> employee = employeeRepository.findByPhonenumber(phoneNumber);
        return employee.map(MapperDTO::employeeDTOMap).orElse(null);
    }

    public void saveOrUpdate(Employee employee){
        employeeRepository.saveAndFlush(employee);
    }

    public void delete(String phoneNum){
        employeeRepository.deleteByPhonenumber(phoneNum);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User emp = employeeRepository.findByPhonenumber(username)
                .map(user -> new User(user.getPhonenumber(),
                        user.getPassword(),
                        user.getRoles())).orElseThrow(() -> new UsernameNotFoundException("Failed to retrive user:" + username));
        return emp;

    }

}
