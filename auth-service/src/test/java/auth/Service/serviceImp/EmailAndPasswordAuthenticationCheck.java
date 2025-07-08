package auth.Service.serviceImp;

import dto.RegisterRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class EmailAndPasswordAuthenticationCheck {
    private jakarta.validation.Validator validator;
    @BeforeEach
    void setUp(){
        ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
        validator=  factory.getValidator();


    }
    @Test
    void inValidMailShouldFail(){
        RegisterRequest request=new RegisterRequest();
        request.setPassword("98108");
        request.setEmail("osazee");
        request.setName("osazee");
        request.setNumber("");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        boolean emailValidation =violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email"));

        assertTrue(emailValidation, "Expected an email validation error.");
    }



    @Test
    void weakPasswordShouldFail() {
        RegisterRequest request = new RegisterRequest();
        request.setPassword("Osazee98108@");
        request.setEmail("bkfhbakf");
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        violations.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));

        boolean passwordValidation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("password"));
        assertTrue(passwordValidation,"Invalid password: password must have upper, lower case and character");


    }


    @Test
    void validRequestShouldPass() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("bkca.s");
        request.setPassword("98108");
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "Expected no validation errors for a valid request.");
    }


}
