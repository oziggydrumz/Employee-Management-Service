package com.example.employee.serviceImp;

import com.example.employee.model.Employee;
import com.example.employee.repo.EmployeeRepo;
import dto.SigningRequest;
import exception.EmployeeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FactoryEmployeeSigningTest {
    @Mock
    private Clock clock;
    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private FactoryEmployeeSigning employeeSigning;

    @BeforeEach
    void  setup(){
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void signInShouldSucceedWithinTimeRange() throws EmployeeException {
        SigningRequest request = new SigningRequest();
        request.setUserId(123);

        Employee employee = new Employee();
        employee.setUserId(123);




        when(employeeRepo.findByUserId(123)).thenReturn(Optional.of(employee));


        LocalTime fakeTime = LocalTime.of(8, 55);
        LocalDateTime fakeDateTime = LocalDateTime.of(LocalDate.now(), fakeTime);
        Clock fixedClock = Clock.fixed(fakeDateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        when(clock.instant()).thenReturn(fixedClock.instant());
        when(clock.getZone()).thenReturn(fixedClock.getZone());

        EmployeeException ex=assertThrows(
                EmployeeException.class,
                ()->employeeSigning.signIn(request));
        assertEquals("You came in late",ex);


        String result = employeeSigning.signIn(request);
        System.out.println("Sign-in success: " + result);
        assertNotNull(result);

    }



    @org.junit.jupiter.api.Test

    void signOutBeforeFiveShouldFail() {
        SigningRequest request = new SigningRequest();
        request.setUserId(123);

        Employee employee = new Employee();
        employee.setUserId(123);

        when(employeeRepo.findByUserId(123)).thenReturn(Optional.of(employee));


        LocalTime fakeTime = LocalTime.of(11, 30);
        LocalDateTime localFakeDateTime = LocalDateTime.of(LocalDate.now(), fakeTime);
        Clock fixClock = Clock.fixed(
                localFakeDateTime.atZone(ZoneId.systemDefault()).toInstant(),
                ZoneId.systemDefault()
        );
        when(clock.instant()).thenReturn(fixClock.instant());
        when(clock.getZone()).thenReturn(fixClock.getZone());

        EmployeeException ex = assertThrows(
                EmployeeException.class,
                () -> employeeSigning.signOut(request)
        );

        assertEquals("You can't sign out before 5 PM", ex.getMessage());
    }

}
