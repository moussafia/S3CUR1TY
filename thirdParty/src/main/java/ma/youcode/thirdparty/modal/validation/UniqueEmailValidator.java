package ma.youcode.thirdparty.modal.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import ma.youcode.thirdparty.repository.UserRepository;

@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private UserRepository userRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email !=null
                &&
                userRepository.findByEmail(email).orElse(null)==null;
    }
}
