package com.example.spring;

import com.example.spring.Model.Department;
import com.example.spring.Model.Employee;
import com.example.spring.Model.PC;
import com.example.spring.Model.Role;
import com.example.spring.Service.DepartmentService;
import com.example.spring.Service.EmployeeService;
import com.example.spring.Service.PCService;
import com.example.spring.Service.RoleService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Transactional
class PhoneBookApplicationTests {
	private final DepartmentService departmentService;
	private final RoleService roleService;
	private final EmployeeService employeeService;
	private final PCService pcService;

	@Test
	void testAddingADepartment() {
		Department department = Department.builder()
				.name("FinanceDepartment")
				.build();

		departmentService.saveAndFlush(department);
		Assertions.assertTrue(departmentService.findByName("FinanceDepartment").isPresent());
	}

	@Test
	void testAddingAPC(){
		PC pc = PC.builder()
				.model("MacBook")
				.ram("4GB")
				.hdd("5TB")
				.inventorynumber("3")
				.build();

		pcService.saveAndFlush(pc);
		Assertions.assertTrue(pcService.findByInvNum("3").isPresent());
	}


}
