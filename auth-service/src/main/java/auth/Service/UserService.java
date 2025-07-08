package auth.Service;

import dto.AuthenticationRequest;
import dto.AuthenticationResponse;
import dto.RegisterRequest;
import exception.EmployeeException;

public interface UserService {

    AuthenticationResponse registration(RegisterRequest request) throws EmployeeException;

    String confirmation(String token) throws EmployeeException;

    AuthenticationResponse login(AuthenticationRequest authRequest);

}
