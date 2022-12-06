package com.example.spring.Controllers;

import com.example.spring.DTO.RoleDTO;
import com.example.spring.Model.Role;
import com.example.spring.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@ResponseBody
public class RolesController {
    private final RoleService roleService;

    @GetMapping("/{page}")
    public Object gettingPage(@PathVariable("page") int page){
        try {
            Pageable pageable = PageRequest.of(page, 2);
            List<RoleDTO> list = roleService.findPage(pageable);
            return (list!=null)&&(!list.isEmpty())?list:"No data";
        } catch (Exception e){
            return "Invalid data";
        }
    }

    @GetMapping("/add/{name}")
    public String addingRole (@PathVariable("name") String name){
        try {
            roleService.saveAndFlush(Role.builder().name(name).build());
            return "";
        } catch (Exception e){
            return "Invalid data";
        }
    }

    @DeleteMapping("/delete/{name}")
    public String deletingRole(@PathVariable("name") String name){
        try {
            roleService.delete(name);
            return "";
        }catch (Exception e){
            return "Invalid data";
        }
    }
}
