package com.sb.springbootrestfullservice.SpringBootServiceImpl;

import com.sb.springbootrestfullservice.SpringBootRepository.EmployeeRepository;
import com.sb.springbootrestfullservice.springBootException.ResourceNotFoundException;
import com.sb.springbootrestfullservice.springBootModel.Employee;
import com.sb.springbootrestfullservice.springBootService.EmployeeService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//       Optional<Employee> employee = employeeRepository.findById(id);
//       if(employee.isPresent()) {
//           return employee.get();
//       }else {
//           throw new ResourceNotFoundException("Employee","Id",id);
//       }
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // сначало нужно проверить существует ли данный сотрудник в БД или нет
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Id", id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        //затем сохраняем в базе данных
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        // сначало нужно сделать проверку на наличие данного id в БД
        employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "ID", id));
        employeeRepository.deleteById(id);
    }
}
