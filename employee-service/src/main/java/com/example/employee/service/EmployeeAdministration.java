package com.example.employee.service;

import exception.EmployeeException;

public interface EmployeeAdministration {
    void deleteEmployee(long id) throws EmployeeException;
    void unSignAdministration(long id) throws EmployeeException;
}
