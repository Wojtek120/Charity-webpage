package pl.coderslab.charity.events;

import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class ResetPasswordListener implements ApplicationListener<OnResetPasswordEvent> {

    private final MessageSource messageSource;
    private final JavaMailSender javaMailSender;

    public ResetPasswordListener(MessageSource messageSource, JavaMailSender javaMailSender) {
        this.messageSource = messageSource;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void onApplicationEvent(OnResetPasswordEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnResetPasswordEvent event) {

        String recipientAddress = event.getUser().getEmail();
        String subject = messageSource.getMessage("reset.password.email-subject", null, event.getLocale());
        String confirmationUrl
                = event.getAppUrl() + "/password/reset/" + event.getToken() + "/" + event.getUser().getId();
        String message = messageSource.getMessage("reset.password.email-registration", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " " + "http://localhost:8080" + confirmationUrl);
        javaMailSender.send(email);
    }
}
