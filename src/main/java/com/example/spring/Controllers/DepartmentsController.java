package com.example.spring.Controllers;

import com.example.spring.DTO.DepartmentDTO;
import com.example.spring.Model.Department;
import com.example.spring.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@ResponseBody
public class DepartmentsController {

    private final DepartmentService departmentService;

    @GetMapping("/{page}")
    public Object gettingPage(@PathVariable("page") int page){
        try {
            Pageable pageable = PageRequest.of(page, 2);
            List<DepartmentDTO> list = departmentService.findPage(pageable);
            return (list!= null)&&(!list.isEmpty())? list : "No data";
        } catch (Exception e) {
            return "Invalid data";
        }
    }

    @GetMapping("/add/{name}")
    public String addingDepartment(@PathVariable("name") String name){
        try {
            departmentService.saveAndFlush(Department.builder().name(name).build());
            return "";
        } catch (Exception e){
            return "Invalid data";
        }
    }

    @GetMapping("/delete/{name}")
    public String deletingDepartment(@PathVariable("name") String name){
        try {
            Optional<Department> department = departmentService.findByName(name);
            if(department.isPresent()) {
                departmentService.delete(department.get());
                return "";
            } else {
                return "Invalid data";
            }
        } catch (Exception e){
            return "Invalid data";
        }
    }
}
