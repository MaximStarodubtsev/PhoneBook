package com.example.spring.DTO;

import lombok.Builder;

@Builder
public record EmployeeDTO (String firstName,
                           String lastName,
                           String patName,
                           String gender,
                           String phoneNum,
                           DepartmentDTO department,
                           PCDTO pc){
}
