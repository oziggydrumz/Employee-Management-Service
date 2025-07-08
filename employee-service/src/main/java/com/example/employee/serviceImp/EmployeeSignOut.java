package com.example.employee.serviceImp;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeSigning;
import dto.SigningRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class EmployeeSignOut implements EmployeeSigning {

    @Override
    public String process(Employee employee) {



        return"sign out successful"+ employee.getRecords();
    }
}
