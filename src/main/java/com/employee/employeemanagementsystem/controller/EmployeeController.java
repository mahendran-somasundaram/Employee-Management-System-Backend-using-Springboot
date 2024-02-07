package com.employee.employeemanagementsystem.controller;

import com.employee.employeemanagementsystem.exception.ResourceNotFoundException;
import com.employee.employeemanagementsystem.model.Employee;
import com.employee.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping()
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }
    @PostMapping()
    public Employee creatEmployee(@RequestBody Employee employee){

        return employeeRepository.save((employee));
    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id:"+id));
        return ResponseEntity.ok(employee);
    }
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employee){
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(("Employee not exist with id:" +id)));
        updateEmployee.setFirstname(employee.getFirstname());
        updateEmployee.setLastname((employee.getLastname()));
        updateEmployee.setEmail(employee.getEmail());
        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);

    }@DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee Details not found"+ id));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
