package com.example.spring.Controllers;

import com.example.spring.Model.Department;
import com.example.spring.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor()
public class LoginController {

    @Autowired
    private final DepartmentService departmentService;

    @ModelAttribute
    public void message(Model model) {
        model.addAttribute("loginMessage", "Hello");
    }

    @GetMapping("/login")
    public String login(){
        Department department = Department.builder()
                .name("HRDepartment")
                .build();
        departmentService.saveAndFlush(department);
        return "/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        Department department = Department.builder()
                .name("HRDepartment")
                .build();
        departmentService.saveAndFlush(department);
        return "/login";
    }
}
