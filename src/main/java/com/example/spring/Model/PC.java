package com.example.spring.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PCs", schema = "phonebook")
public class PC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String model;
    @Column
    private String hdd;
    @Column
    private String ram;
    @Column
    private String inventorynumber;

    @OneToOne(mappedBy = "pc", fetch = FetchType.LAZY)
    private Employee employee;
}
