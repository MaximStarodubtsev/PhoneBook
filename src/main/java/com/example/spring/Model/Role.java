package com.example.spring.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles", schema = "phonebook")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "employee_role",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "employee_id") }
    )
    List<Employee> employees = new ArrayList<>();
}
