package pl.coderslab.charity.utils;

import org.passay.*;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import pl.coderslab.charity.utils.annotations.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private final MessageSource messageSource;

    public PasswordConstraintValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {


        PasswordValidator validator = new PasswordValidator(
                new CharacterRule(PolishCharacterData.UpperCase, 1),
                new CharacterRule(PolishCharacterData.LowerCase, 1),
                new CharacterRule(PolishCharacterData.Digit, 1),
                new CharacterRule(PolishCharacterData.Special, 1),
                new WhitespaceRule()
        );

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

//        String messageTemplate = String.join("\n", messages);
        context.buildConstraintViolationWithTemplate(messageSource.getMessage("password.verification-wrong.pass.msg", null, LocaleContextHolder.getLocale()))
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
