package vulnlab.configsecu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

/** web.ignoring() sur des ressources statiques : acceptable. */
@Configuration
public class ConfigIgnoringStatic {
    @Bean
    public WebSecurityCustomizer ignoreStatic() {
        return web -> web.ignoring().requestMatchers("/css/**", "/js/**", "/favicon.ico");
    }
}
