package com.example.employee.serviceImp;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeSigning;
import dto.SigningRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeSingIn implements EmployeeSigning {


    @Override
    public String process(Employee  employee) {





        return"Sign-in successful:" + employee.getRecords();
    }
}
