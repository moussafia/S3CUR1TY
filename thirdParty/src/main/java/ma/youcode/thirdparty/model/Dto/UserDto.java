package ma.youcode.thirdparty.model.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.thirdparty.userDetails.validation.PasswordConfirmed;
import ma.youcode.thirdparty.userDetails.validation.PasswordPolicy;
import ma.youcode.thirdparty.userDetails.validation.UniqueEmail;
import ma.youcode.thirdparty.userDetails.validation.UniqueUsername;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@PasswordConfirmed
public class UserDto {
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
	@Min(4)
	private int securityPin;
	@Min(4)
	private int confirmSecurityPin;

}
