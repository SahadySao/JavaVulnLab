package vulnlab.configsecu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/** Chaîne correcte : CSRF actif, en-têtes par défaut, frameOptions sameOrigin. */
@Configuration
public class ConfigSecureChain {
    @Bean
    public SecurityFilterChain configSecureChainChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/configsecu/secure/**")
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/login"))
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        return http.build();
    }
}
