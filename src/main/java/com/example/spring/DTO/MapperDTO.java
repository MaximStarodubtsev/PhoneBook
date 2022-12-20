package com.example.spring.DTO;

import com.example.spring.Model.Department;
import com.example.spring.Model.Employee;
import com.example.spring.Model.PC;
import com.example.spring.Model.Role;

public class MapperDTO {
    public static DepartmentDTO departmentDTOMap(Department department){
        return DepartmentDTO.builder()
                .name(department.getName())
                .build();
    }

    public static PCDTO pcDTOMap(PC pc){
        return PCDTO.builder()
                .model(pc.getModel())
                .HDD(pc.getHdd())
                .RAM(pc.getRam())
                .invNum(pc.getInventorynumber())
                .build();
    }

    public static RoleDTO roleDTOMap(Role role){
        return RoleDTO.builder()
                .name(role.getName())
                .build();
    }

    public static EmployeeDTO employeeDTOMap(Employee employee){
        return EmployeeDTO.builder()
                .firstName(employee.getFirstname())
                .lastName(employee.getLastname())
                .patName(employee.getPatronymicname())
                .gender(employee.getGender())
                .phoneNum(employee.getPhonenumber())
                .pc(pcDTOMap(employee.getPc()))
                .department(departmentDTOMap(employee.getDepartment()))
                .build();
    }

}
