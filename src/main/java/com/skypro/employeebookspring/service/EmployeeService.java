package com.skypro.employeebookspring.service;

import com.skypro.employeebookspring.exeption.EmployeeNotFoundException;
import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeService {

    private final Map<Integer, Employee> employees = new HashMap<>();


    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee name should be set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;

    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getEmployeeWithMinSalary() throws EmployeeNotFoundException {
        return employees.values().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);

    }

    public Employee getEmployeeWithMaxSalary() {
        return employees.values().stream().min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(()-> new EmployeeNotFoundException());
    }

    public List<Employee> getEmployeeWithSalaryMoreThatAverage() {
        return null;
    }
}
