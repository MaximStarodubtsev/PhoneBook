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
				.name("HRDepartment")
				.build();

		departmentService.saveAndFlush(department);
		Assertions.assertTrue(departmentService.findByName("HRDepartment").isPresent());
	}

	@Test
	void testFindingADepartment() {
		Assertions.assertTrue(departmentService.findByName("HRDepartment").isPresent());
	}


	@Test
	void testAddingARole() {
		Role role = Role.builder()
				.name("ADMIN")
				.build();

		roleService.saveAndFlush(role);
		Assertions.assertTrue(roleService.findByName("ADMIN").isPresent());
	}

	@Test
	void testAddingAPC(){
		PC pc = PC.builder()
				.model("Acer")
				.ram("4GB")
				.hdd("5TB")
				.inventorynumber("1111")
				.build();

		pcService.saveAndFlush(pc);
		Assertions.assertTrue(pcService.findByInvNum("1111").isPresent());
	}


	@Test
	void testAddingAnEmployee() {
		Department department = Department.builder()
				.name("HRDepartment")
				.build();
		departmentService.saveAndFlush(department);
		Role role = Role.builder()
				.name("ADMIN")
				.build();
		roleService.saveAndFlush(role);
		PC pc = PC.builder()
				.model("Acer")
				.ram("4GB")
				.hdd("5TB")
				.inventorynumber("1111")
				.build();
		pcService.saveAndFlush(pc);

		Employee employee = Employee.builder()
						.firstname("Ivan")
						.patronymicname("Ivanovich")
						.lastname("Ivanov")
						.phonenumber("+375297777777")
						.gender("MALE")
						.department(department)
						.roles(List.of(roleService.findByName("ADMIN").get()))
						.pc(pcService.findByInvNum("1111").get())
						.build();

		employeeService.saveOrUpdate(employee);
		Assertions.assertTrue(employeeService.findByPhone("+375297777777").isPresent());
	}

}
