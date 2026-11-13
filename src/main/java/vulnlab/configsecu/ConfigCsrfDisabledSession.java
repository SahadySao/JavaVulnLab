package vulnlab.configsecu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/** CSRF désactivé avec une connexion par formulaire (cookie de session). */
@Configuration
public class ConfigCsrfDisabledSession {
    @Bean
    public SecurityFilterChain configCsrfDisabledSessionChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/configsecu/csrf-session/**")
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/login"))
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
