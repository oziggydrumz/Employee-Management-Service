package com.example.employee.serviceImp;

import com.example.employee.model.Employee;
import com.example.employee.repo.EmployeeRepo;
import com.example.employee.service.EmployeeAdministration;
import exception.EmployeeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RequiredArgsConstructor
public class EmployeeAdministrationImp implements EmployeeAdministration {

    private final EmployeeRepo repo;

    @Override
    public void deleteEmployee(long id) throws EmployeeException {
       Optional<Employee> employee1 = repo.findByUserId(id);
        if (employee1.isEmpty()) {
            throw new EmployeeException("the employee you are trying to delete is not register", HttpStatus.NOT_FOUND);
        }
        Employee employee=employee1.get();
        employee.setSignOut(null);
        employee.setSignIn(null);
        employee.setRecords(null);
        repo.deleteById(id);
    }

        @Override
        public void unSignAdministration ( long id) throws EmployeeException {

           Optional<Employee> employee1 = repo.findByUserId(id);
            if (employee1.isEmpty()) {
                throw new EmployeeException("the employee you are trying to delete is not register", HttpStatus.NOT_FOUND);
            }
            Employee employee=employee1.get();

            employee.setEnableSigningIn(false);
            repo.save(employee);


        }
    }

