package ma.youcode.thirdparty.modal.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import ma.youcode.thirdparty.modal.validation.PasswordConfirmed;
import ma.youcode.thirdparty.modal.validation.PasswordPolicy;
import ma.youcode.thirdparty.modal.validation.UniqueEmail;
import ma.youcode.thirdparty.modal.validation.UniqueUsername;
@Getter
@Setter
@PasswordConfirmed
public class UserRegistrationRequest {
    @NotEmpty(message="Please enter your firstname")
    private String firstname;
    @NotEmpty(message="Please enter your lastname")
    private String lastname;
    @NotEmpty(message="Please enter a username")
    @UniqueUsername
    private String username;
    @NotEmpty(message="Please enter an email")
    @Email(message="Email is not valid")
    @UniqueEmail
    private String email;
    @NotEmpty(message="Please enter in a password")
    @PasswordPolicy
    private String password;
    @NotEmpty(message="Please confirm your password")
    private String confirmPassword;

}
