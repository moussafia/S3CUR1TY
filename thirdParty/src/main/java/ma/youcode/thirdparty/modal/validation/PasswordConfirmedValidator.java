package ma.youcode.thirdparty.modal.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ma.youcode.thirdparty.modal.Dto.UserDto;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, Object> {

    @Override
    public boolean isValid(Object user, ConstraintValidatorContext constraintValidatorContext) {
        String password = ((UserDto)user).getPassword();
        String passwordConfirmed = ((UserDto)user).getConfirmPassword();
        return password.equals(passwordConfirmed);
    }
}
