package vulnlab.configsecu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/** X-Frame-Options désactivé : pages intégrables dans un iframe (clickjacking). */
@Configuration
public class ConfigFrameOptionsDisabled {
    @Bean
    public SecurityFilterChain configFrameOptionsDisabledChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/configsecu/frames/**")
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }
}
