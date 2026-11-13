package vulnlab.bfla;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/** Configuration Spring Security des cas bfla (pas un cas) : limitée à /bfla/** par securityMatcher. */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain bflaChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/bfla/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/bfla/admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/bfla/reports/**").hasRole("MANAGER")
                .requestMatchers("/bfla/public/**").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }
}
