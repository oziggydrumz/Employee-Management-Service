package model.auth;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import model.auth.PasswordStrengthValidation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidation.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message()default "password must be strong: at least 1 character, 1 upperCase, 1 lowerCase, 1 digit";
    Class<?>[] groups() default  {};
    Class<? extends Payload> [] payload() default {};
}
