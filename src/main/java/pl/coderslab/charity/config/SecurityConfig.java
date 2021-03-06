package pl.coderslab.charity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final RoleBasedAuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(DataSource dataSource, RoleBasedAuthenticationSuccessHandler authenticationSuccessHandler){
        this.dataSource = dataSource;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT email, password, enabled FROM users WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT charity_donation.users.email, charity_donation.user_authorities.authority FROM users INNER JOIN user_authorities ON users.id = user_authorities.user_id WHERE users.email = ?");
//                .authoritiesByUsernameQuery("SELECT email, authority FROM user_authorities WHERE email = ?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/registration", "/registration/**").permitAll()
                    .antMatchers("/password/reset", "/password/reset/**").permitAll()
                    .antMatchers("/password/reset/edit").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                    .antMatchers("/donation", "/donation/**").hasRole("USER")
                    .antMatchers("/admin", "/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
//                        .defaultSuccessUrl("/")
                        .successHandler(authenticationSuccessHandler)
                        .failureUrl("/login?error=true")
                        .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                .and()
                    .csrf()
                .and();

    }
}
