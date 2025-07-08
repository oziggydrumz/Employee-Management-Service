package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import model.auth.StrongPassword;

import javax.xml.transform.Source;

@Data
public class RegisterRequest  {
    @NotBlank(message = "Email cannot be blank")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Email cannot be blank")
    private String number;

    @StrongPassword
    private String password;

}
