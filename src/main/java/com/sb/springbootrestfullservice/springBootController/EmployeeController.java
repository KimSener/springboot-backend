package com.sb.springbootrestfullservice.springBootController;

import com.sb.springbootrestfullservice.springBootModel.Employee;
import com.sb.springbootrestfullservice.springBootService.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // создание Сотрудника REST API
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // вывести список всех сотрудников
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // вывести сотрудника по id
    @GetMapping("{id}")
    //http://localhost:8080/api/employees/1 or 2 or 3 and .....or how many employees
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeid) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeid), HttpStatus.OK);
    }

    // обновление изменение сотрудников
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id
            , @RequestBody Employee employee) {

        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    // удаление сотрудников
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(" Employee deleted successfully",HttpStatus.OK);
    }

}
