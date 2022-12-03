package com.example.spring.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;
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
@Table(name = "employees", schema = "phonebook")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firstname;
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

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    List<Role> roles = new ArrayList<>();
}
