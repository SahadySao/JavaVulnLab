package vulnlab.configsecu;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/** Spring Security en mode débogage. */
@Configuration
@EnableWebSecurity(debug = true)
public class ConfigDebug {
}
