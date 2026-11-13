package vulnlab.configsecu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Mots de passe stockés en clair. */
@Configuration
public class ConfigNoOpEncoder {
    @Bean
    public PasswordEncoder plainEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
