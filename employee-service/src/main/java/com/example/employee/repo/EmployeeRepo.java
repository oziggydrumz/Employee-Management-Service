package com.example.employee.repo;

import com.example.employee.model.Employee;
import org.apache.kafka.shaded.com.google.protobuf.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    void deleteUserById(Long userId);

    void saveById(long id );

   Optional<Employee> findByUserId(long userId);


}
