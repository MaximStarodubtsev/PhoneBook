package com.example.spring.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(name = "RoleWithEmployees",
        attributeNodes = {
                @NamedAttributeNode("employees")
        }
)
@Entity
@Component
@ToString(exclude = "employees")
@EqualsAndHashCode(of = "name")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles", schema = "phonebook")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    List<Employee> employees = new ArrayList<>();

    @Override
    public String getAuthority(){
        return name;
    }
}
