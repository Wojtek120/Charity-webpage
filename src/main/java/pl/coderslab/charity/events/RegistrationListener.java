package pl.coderslab.charity.events;

import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.services.UserService;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final UserService userService;
    private final MessageSource messageSource;
    private final JavaMailSender javaMailSender;

    public RegistrationListener(UserService userService, MessageSource messageSource, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.messageSource = messageSource;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = messageSource.getMessage("authentication.email-subject", null, event.getLocale());
        String confirmationUrl
                = event.getAppUrl() + "/registration/confirm/" + token;
        String message = messageSource.getMessage("authentication.email-registration", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " " + "http://localhost:8080" + confirmationUrl);
        javaMailSender.send(email);
    }
}
