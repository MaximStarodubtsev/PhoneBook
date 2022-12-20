package com.example.spring.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@NamedEntityGraph(name="withEmployees",
        attributeNodes = {
            @NamedAttributeNode("employees")
        }
)
@Entity
@Component
@EqualsAndHashCode(of = "name")
@ToString(exclude = "employees")
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
