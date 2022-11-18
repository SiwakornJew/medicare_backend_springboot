package com.medicare_backend.medicare_backend.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.medicare_backend.medicare_backend.repository.EmployeeRepository;
import com.medicare_backend.medicare_backend.schema.entity.Employee;
import com.medicare_backend.medicare_backend.strategyInterface.GetEmployeeStrategy;

public class GetDoctorEmployee implements GetEmployeeStrategy {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    public List<Employee> getEmployee() {
        return employeeRepository.findByEmployeeRole((long) 0);
    }

}
