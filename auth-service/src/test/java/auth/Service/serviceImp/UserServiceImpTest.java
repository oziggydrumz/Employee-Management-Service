package auth.Service.serviceImp;

import auth.mapper.OtpGenerator;
import auth.mapper.UserMapper;
import auth.model.MyRole;
import auth.model.OtpValidation;
import auth.repo.RoleRepo;
import auth.repo.ValidationRepo;
import dto.AuthenticationResponse;
import dto.RegisterRequest;
import exception.EmployeeException;
import jakarta.annotation.Nullable;

import model.Role;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImpTest {
    @Mock
    private repo.UserRepo userRepo;
    @Mock
    private RoleRepo roleRepo;

    @Mock
    private ValidationRepo validationRepo;

    @InjectMocks
    private UserServiceImp userService;



    @Mock
    private PasswordEncoder passwordEncoder;


    @Mock
    private OtpGenerator otpGenerator;


    @Mock
     private UserMapper userMapper;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registration() throws EmployeeException {

       RegisterRequest request=new RegisterRequest();
       request.setPassword("98108");
       request.setEmail("bakbk");
       request.setNumber("098108");
       request.setName("abkfb");

       MyRole role =new MyRole();
        role.setTitle(Role.USER.getValue());

        OtpValidation validation=new OtpValidation();
        validation.setToken("9810");

        User user=new User();
        user.setEmail(request.getEmail());
        user.setPassWord("encodePassword");
        when(userRepo.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(otpGenerator.generate()).thenReturn(validation.getToken());
        when(userMapper.map(any(RegisterRequest.class))).thenReturn(user);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodePassword");
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(roleRepo.findByTitle(Role.USER.getValue())).thenReturn(Optional.ofNullable(role));
        AuthenticationResponse response = (userService.registration(request));
        assertEquals("9810",response.getToken());



    }

    @Test
    void confirmation() {
        String token="9810";
        when(validationRepo.findByToken(token)).thenReturn(Optional.empty());
        assertThrows(EmployeeException.class,()->{
            userService.confirmation(token);
        });





    }
    @Test
    void  otpConfirmation(){
        OtpValidation validation=new OtpValidation();
        validation.setToken("9820");
        validation.setExpiredOn(null);
        when(validationRepo.findByToken("9820")).thenReturn(Optional.of(validation));
        assertThrows(EmployeeException.class,()->{
            userService.confirmation("9820");
        });



    }
    @Test
    void otpExpired(){
        OtpValidation validation=new OtpValidation();
        validation.setToken("9810");
        validation.setExpiredOn(LocalDateTime.now().plusMinutes(5));
        when(validationRepo.findByToken("98108")).thenReturn(Optional.of(validation));
        assertThrows(EmployeeException.class,()->{
            EmployeeException employeeException=new EmployeeException("", HttpStatus.NOT_FOUND);
            System.out.println(employeeException.getMessage());
            userService.confirmation("98108");
        });

    }

    @Test
    void login() {
    }
}