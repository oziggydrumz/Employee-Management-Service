package auth.Service.serviceImp;

import auth.Service.UserService;
import auth.mapper.OtpGenerator;

import auth.mapper.UserMapper;
import auth.model.MyRole;

import auth.model.OtpValidation;
import auth.repo.RoleRepo;

import auth.repo.ValidationRepo;
import dto.AuthenticationRequest;
import dto.AuthenticationResponse;
import dto.RegisterRequest;
import exception.EmployeeException;
import lombok.RequiredArgsConstructor;


import model.Role;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import repo.UserRepo;

import security.config.JwtService;

import java.time.LocalDateTime;
import java.util.Optional;
@RequiredArgsConstructor

@Service

public class UserServiceImp implements UserService {
    private final UserRepo userRepo;
    private final UserMapper mapper;

    private  final RoleRepo roleRepo;

    private final ValidationRepo validationRepo;
    private final OtpGenerator otpGenerator;

    private final JwtService jwtService;

    private  final AuthenticationManager authManager;



    @Override
    public AuthenticationResponse registration(RegisterRequest request) throws EmployeeException {
        Optional<User> findUser = userRepo.findByEmail(request.getEmail());
        if (findUser.isPresent()) {
            throw new EmployeeException("user already exist", HttpStatus.ALREADY_REPORTED);
        }
        User user = mapper.map(request);

        Optional<MyRole>roles=roleRepo.findByTitle(Role.USER.getValue());
        if (roles.isEmpty()){
            throw new EmployeeException("Role not found",HttpStatus.NOT_FOUND);

        }
        user.getAppRoles().add(roles.get());


        userRepo.save(user);


        String otpGen = otpGenerator.generate();
        OtpValidation validation = new OtpValidation();
        validation.setUser(user);
        validation.setToken(otpGen);
        validation.setCreatedOn(LocalDateTime.now());
        validation.setConfirmedOn(LocalDateTime.now());
        validation.setExpiredOn(LocalDateTime.now().plusMinutes(60));

        return AuthenticationResponse.builder()
                .token(otpGen)
                .build();


    }

    @Override
    public String confirmation(String token) throws EmployeeException {

        Optional<OtpValidation> validation = validationRepo.findByToken(token);
        if (validation.isEmpty()) {
            throw new EmployeeException("unKnow token", HttpStatus.NOT_FOUND);
        }

        OtpValidation validation1 = validation.get();

        LocalDateTime expiredAt = validation1.getExpiredOn();
        LocalDateTime confirmedAt = validation1.getConfirmedOn();

        if (confirmedAt == null) {
            throw new EmployeeException("account has not been confirmed", HttpStatus.NOT_FOUND);

        }

        boolean expired = expiredAt.isBefore(LocalDateTime.now());
        User user=new User();
        if (expired) {
            validation1.setUser(null);
            user.setAppRoles(null);
            validationRepo.delete(validation1);
            userRepo.delete(user);

            return "expired  token";
        }
        user.setEnable(true);
        userRepo.save(user);
        validationRepo.save(validation1);


        return "account confirm";


    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {

        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getPassword(),
                request.getEmail()
        ));
        var jwtToken = jwtService.generateToken(request.getEmail());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();



    }
}
