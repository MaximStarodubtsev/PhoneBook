package com.example.spring.DTO;

import lombok.Builder;

import java.util.List;

@Builder
public record EmployeeDTO (String firstName,
                           String lastName,
                           String patName,
                           String gender,
                           String phoneNum,
                           DepartmentDTO department,
                           PCDTO pc,
                           List<RoleDTO> roles){
}
