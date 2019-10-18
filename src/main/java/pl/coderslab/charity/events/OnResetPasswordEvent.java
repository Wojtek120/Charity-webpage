package pl.coderslab.charity.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import pl.coderslab.charity.model.entities.User;

import java.util.Locale;

@Getter @Setter
public class OnResetPasswordEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private User user;
    private String token;

    public OnResetPasswordEvent(User user, Locale locale, String appUrl, String token) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
        this.token = token;
    }
}
