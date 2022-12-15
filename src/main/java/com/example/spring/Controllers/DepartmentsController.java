package com.example.spring.Controllers;

import com.example.spring.DTO.DepartmentDTO;
import com.example.spring.Model.Department;
import com.example.spring.Service.DepartmentService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@ResponseBody
@Validated
public class DepartmentsController {

    private final static String errorMessage = "Invalid data or system error";
    private final DepartmentService departmentService;

    @GetMapping("/{page}")
    public Object gettingPage(@PathVariable("page") @Min(0) @Max(Integer.MAX_VALUE) int page){
        try {
            Pageable pageable = PageRequest.of(page, 2);
            List<DepartmentDTO> list = departmentService.findPage(pageable);
            return (list!= null)&&(!list.isEmpty())? list : "No data";

        } catch (Exception e) {
            return errorMessage;
        }
    }

    @GetMapping("/add/{name}")
    public String addingDepartment(@PathVariable("name") @Size(max=136) String name){
        try {
            departmentService.saveAndFlush(Department.builder().name(name).build());
            return "";
        } catch (Exception e){
            return errorMessage;
        }
    }

    @GetMapping("/delete/{name}")
    public String deletingDepartment(@PathVariable("name") @Size(max=136) String name){
        try {
            Optional<Department> department = departmentService.findByName(name);
            if(department.isPresent()) {
                departmentService.delete(department.get());
                return "";
            } else {
                return errorMessage;
            }
        } catch (Exception e){
            return errorMessage;
        }
    }
}
