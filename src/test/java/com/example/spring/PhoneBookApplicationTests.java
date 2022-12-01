package com.example.spring;

import com.example.spring.Model.Department;
import com.example.spring.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Transactional
class PhoneBookApplicationTests {

	private final DepartmentService departmentService;

	@Test
	void testAddingADepartment() {
		Department department = Department.builder()
				.name("HRDepartment")
				.build();

		departmentService.saveAndFlush(department);
		Assertions.assertTrue(departmentService.findByName("HRDeprtment").isPresent());
	}

}
