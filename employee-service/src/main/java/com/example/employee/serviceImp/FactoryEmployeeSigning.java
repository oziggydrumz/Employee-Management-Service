package com.example.employee.serviceImp;


import com.example.employee.model.Employee;
import com.example.employee.repo.EmployeeRepo;
import dto.SigningRequest;
import exception.EmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.Clock;
import java.time.LocalTime;
import java.util.Optional;


public class FactoryEmployeeSigning  {


        private final EmployeeRepo repo;

          private final Clock clock;



          @Autowired
    public FactoryEmployeeSigning(EmployeeRepo repo, Clock clock) {
        this.repo = repo;
        this.clock = clock;
    }

    public String signIn(SigningRequest request) throws EmployeeException {
            Employee employee = getEmployeeOrThrow(request);

            LocalTime now = LocalTime.now(clock);
            LocalTime signInStart = LocalTime.of(7, 55);
            LocalTime signInEnd = LocalTime.of(8, 10);

            if (now.isAfter(signInStart)) {
                throw new EmployeeException("You came in late", HttpStatus.FORBIDDEN);
            }

            employee.setSignIn(now); // safe
            employee.setRecords("Good one, meeting up with the time");
            employee.setEnableSigningIn(true);
            repo.save(employee);

            return new EmployeeSingIn().process(employee);


        }

    public String signOut(SigningRequest request) throws EmployeeException {
        Employee employee=getEmployeeOrThrow(request);

        LocalTime now = LocalTime.now(clock);
        LocalTime signOutTime = LocalTime.of(17, 0);

        if (now.isBefore(signOutTime)) {
            throw new EmployeeException("You can't sign out before 5 PM", HttpStatus.FORBIDDEN);
        }

        employee.setSignOut(LocalTime.now(clock)); // ✅ use clock here too

        if (now.equals(signOutTime)) {
            employee.setRecords("Nice! You closed at the right time.");
        } else {
            employee.setRecords("Additional hours! Good work.");
        }

        repo.save(employee);
        return new EmployeeSignOut().process(employee);
    }

    private Employee getEmployeeOrThrow(SigningRequest request) throws EmployeeException {
            Optional<Employee> employee = repo.findByUserId(request.getUserId());
            if (employee.isEmpty()) {
                throw new EmployeeException("The employee is not a user", HttpStatus.NOT_FOUND);

            }
            Employee employee1=employee.get();
            return employee1;
        }

    }



