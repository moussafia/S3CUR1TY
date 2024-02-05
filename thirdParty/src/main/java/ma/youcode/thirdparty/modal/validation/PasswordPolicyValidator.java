package ma.youcode.thirdparty.modal.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyValidator implements ConstraintValidator<PasswordPolicy, String> {
    private static final int MIN_COMPLEX_RULES = 3;
    private static final int MIN_UPPER_CASE_CHARS = 1;
    private static final int MIN_LOWER_CASE_CHARS = 1;
    private static final int MIN_DIGIT_CASE_CHARS = 1;
    private static final int MIN_SPECIAL_CASE_CHARS = 1;
    private static final int MAX_REPETITIVE_CHARS = 3;
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        List<Rule> passwordRules = new ArrayList<>();
        passwordRules.add(new LengthRule(10,30));
        CharacterCharacteristicsRule passwordRule = new CharacterCharacteristicsRule(
                MIN_COMPLEX_RULES,
                new CharacterRule(EnglishCharacterData.Digit, MIN_DIGIT_CASE_CHARS),
                new CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
                new CharacterRule(EnglishCharacterData.LowerCase, MIN_LOWER_CASE_CHARS),
                new CharacterRule(EnglishCharacterData.Special, MIN_SPECIAL_CASE_CHARS)
        );
        passwordRules.add(passwordRule);
        passwordRules.add(new RepeatCharacterRegexRule(MAX_REPETITIVE_CHARS));
        PasswordValidator passwordValidator = new PasswordValidator(passwordRules);
        PasswordData passwordData = new PasswordData(password);
        RuleResult ruleResult = passwordValidator.validate(passwordData);
        return ruleResult.isValid();
    }
}
