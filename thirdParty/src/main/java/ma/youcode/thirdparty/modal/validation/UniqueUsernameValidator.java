package ma.youcode.thirdparty.modal.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import ma.youcode.thirdparty.repository.UserRepository;

@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private UserRepository userRepository;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

        return username!=null
                && userRepository.findByUsername(username)
                .orElse(null) == null ;
    }
}
