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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@ResponseBody
public class EmployeesController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PCService pcService;
    private final RoleService roleService;

    @GetMapping("/{page}")
    public Object gettingEmployees(@PathVariable("page") int page){
        try {
            Pageable pageable = PageRequest.of(page, 2);
            List<EmployeeDTO> list = employeeService.findPage(pageable);
            return (list!=null)&&(!list.isEmpty())? list : "No data";
        } catch (Exception e){
            return "Invalid data";
        }
    }

    @GetMapping("/add/{firstName}/{lastName}/{patName}/{gender}/{phoneNum}/{depName}/{pcInvNum}/{roleName}")
    public String addingEmployee(@PathVariable("firstName") String firstName,
                               @PathVariable("lastName") String lastName,
                               @PathVariable("patName") String patName,
                               @PathVariable("gender") String gender,
                               @PathVariable("phoneNum") String phoneNum,
                               @PathVariable("depName") String depName,
                               @PathVariable("pcInvNum") String pcInvNum,
                               @PathVariable("roleName") String roleName)
    {
        try {
            Optional<Department> department = departmentService.findByName(depName);
            Optional<PC> pc = pcService.findByInvNum(pcInvNum);
            Optional<Role> role = roleService.findByName(roleName);
            if (department.isPresent() && pc.isPresent() && role.isPresent()) {
                Employee employee = Employee.builder()
                        .firstname(firstName)
                        .lastname(lastName)
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
            return "Invalid data";
        }
        return "";
    }

    @DeleteMapping("/delete/{phoneNum}")
    public String deletingEmployee(@PathVariable("phoneNum") String phoneNum){
        try {
            employeeService.delete(phoneNum);
            return "";
        } catch (Exception e) {
            return "Invalid data";
        }
    }
}
