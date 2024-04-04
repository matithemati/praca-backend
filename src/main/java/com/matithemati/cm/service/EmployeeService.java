package com.matithemati.cm.service;

import com.matithemati.cm.model.Employee;
import com.matithemati.cm.repository.EmployeeRepository;
import com.matithemati.cm.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ReservationRepository reservationRepository;

    public Employee addEmployee(Employee employee) {
        if (employeeRepository.findEmployeeByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalStateException("Email is already taken");
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + id + " does not exist"));
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + id + " does not exist"));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        if (reservationRepository.existsByEmployeeId(id)) {
            throw new IllegalStateException("Employee with id " + id + " has reservations");
        }
        employeeRepository.deleteById(id);
    }
}
