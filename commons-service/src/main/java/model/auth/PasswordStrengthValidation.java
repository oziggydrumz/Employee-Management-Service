package model.auth;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordStrengthValidation implements ConstraintValidator<StrongPassword,String> {

    private static  final String STRING_STRONG_PASSWORD_REGEX=
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#&%!*?])[A-Za-z\\d@#&%!*?]{8,}$";
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password !=null && password.matches(STRING_STRONG_PASSWORD_REGEX);
    }
}
