package vulnlab.configsecu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

/** web.ignoring() sur une API : hors de Spring Security. */
@Configuration
public class ConfigIgnoringApi {
    @Bean
    public WebSecurityCustomizer ignoreApi() {
        return web -> web.ignoring().requestMatchers("/configsecu/internal-api/**");
    }
}
