package com.example.spring.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments", schema = "phonebook")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "department")
    List<Employee> employees;
}
