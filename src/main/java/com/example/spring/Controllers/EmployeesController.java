package com.example.spring.Controllers;

import com.example.spring.DTO.EmployeeDTO;
import com.example.spring.Model.Department;
import com.example.spring.Model.Employee;
import com.example.spring.Model.PC;
import com.example.spring.Model.Role;
import com.example.spring.Service.DepartmentService;
import com.example.spring.Service.EmployeeService;
import com.example.spring.Service.PCService;
import com.example.spring.Service.RoleService;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@ResponseBody
public class EmployeesController {

    private final static String errorMessage = "Invalid data or system error";
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PCService pcService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/{page}")
    public Object gettingEmployees(@PathVariable("page") @Min(0) @Max(Integer.MAX_VALUE) int page){
        try {
            Pageable pageable = PageRequest.of(page, 2);
            List<EmployeeDTO> list = employeeService.findPage(pageable);
            return (list!=null)&&(!list.isEmpty())? list : "No data";
        } catch (Exception e){
            return errorMessage;
        }
    }

    @GetMapping("/registration/{firstName}/{lastName}/{patName}/{password}/{gender}/{phoneNum}/{depName}/{pcInvNum}/{roleName}")
    public String addingEmployee(@PathVariable("firstName") @Size(max=136) String firstName,
                               @PathVariable("lastName") @Size(max=136) String lastName,
                               @PathVariable("patName") @Size(max=136) String patName,
                               @PathVariable("password") @Size(max=136) @NotEmpty @NotNull Object password,
                               @PathVariable("gender") @Size(max=136) String gender,
                               @PathVariable("phoneNum") @Size(max=136) @NotEmpty @NotNull String phoneNum,
                               @PathVariable("depName")  String depName,
                               @PathVariable("pcInvNum")  String pcInvNum,
                               @PathVariable("roleName")  String roleName)
    {
        try {
            Optional<Department> department = departmentService.findByName(depName);
            Optional<PC> pc = pcService.findByInvNum(pcInvNum);
            Optional<Role> role = roleService.findByName(roleName);
            if (department.isPresent() && pc.isPresent() && role.isPresent() ) {
                password = passwordEncoder.encode(password.toString());
                Employee employee = Employee.builder()
                        .firstname(firstName)
                        .lastname(lastName)
                        .password(password.toString())
                        .patronymicname(patName)
                        .gender(gender)
                        .phonenumber(phoneNum)
                        .department(department.get())
                        .pc(pc.get())
                        .build();
                employee.addRole(role.get());
                employeeService.saveOrUpdate(employee);
            } else {
                throw new Exception();
            }
        } catch (Exception exception){
            return errorMessage;
        }
        return "";
    }

    @DeleteMapping("/delete/{phoneNum}")
    public String deletingEmployee(@PathVariable("phoneNum") @Size(max=136) @NotEmpty @NotNull String phoneNum){
        try {
            employeeService.delete(phoneNum);
            return "";
        } catch (Exception e) {
            return errorMessage;
        }
    }
}