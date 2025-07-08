package com.example.employee.serviceImp;

import com.example.employee.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import model.UserRoleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
@RequiredArgsConstructor
public class UserRoleListener {
    @Autowired
    private final EmployeeRepo repo;

    @KafkaListener(topics = "user-role-events", groupId = "employee-group")
    public void handleUserRoleEvent(UserRoleEvent event) {
        if (event.getRoles().contains("EMPLOYEE")) {
            repo.saveById(event.getUserId());
        } else {
            repo.deleteUserById(event.getUserId());
        }

    }
}
