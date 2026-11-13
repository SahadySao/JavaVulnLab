package vulnlab.configsecu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/** Identifiant de session conservé à la connexion (fixation de session). */
@Configuration
public class ConfigSessionFixationNone {
    @Bean
    public SecurityFilterChain configSessionFixationNoneChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/configsecu/session/**")
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .sessionManagement(session -> session.sessionFixation(fixation -> fixation.none()));
        return http.build();
    }
}
