package com.sb.springbootrestfullservice.SpringBootRepository;

import com.sb.springbootrestfullservice.springBootModel.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
