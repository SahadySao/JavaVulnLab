package vulnlab.configsecu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
/** API sans session (STATELESS, en-tête Authorization) : CSRF désactivé à juste titre. */
@Configuration
public class ConfigCsrfDisabledStateless {
    @Bean
    public SecurityFilterChain configCsrfDisabledStatelessChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/configsecu/api-jwt/**")
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(basic -> {})
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
