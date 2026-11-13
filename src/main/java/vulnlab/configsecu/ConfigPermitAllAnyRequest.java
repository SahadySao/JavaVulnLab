package vulnlab.configsecu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/** Chaîne entièrement ouverte : anyRequest().permitAll(). */
@Configuration
public class ConfigPermitAllAnyRequest {
    @Bean
    public SecurityFilterChain configPermitAllAnyRequestChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/configsecu/open/**")
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
