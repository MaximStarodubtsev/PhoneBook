package com.example.spring.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
@ToString(exclude = "roles")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees", schema = "phonebook")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firstname;
    @Column
    private String password;
    @Column
    private String lastname;
    @Column
    private String patronymicname;
    @Column
    private String gender;
    @Column
    private String phonenumber;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_id")
    private PC pc;

    @Builder.Default
    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "employees_roles", schema = "phonebook",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    List<Role> roles = new ArrayList<>();

    public void addRole(Role role){
        roles.add(role);
        role.getEmployees().add(this);
    }
}
