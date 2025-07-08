package com.example.employee.service;

import com.example.employee.model.Employee;
import dto.SigningRequest;
import exception.EmployeeException;

public interface EmployeeSigning {
    String process(Employee employee) throws EmployeeException;

}
