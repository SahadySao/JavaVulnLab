package vulnlab.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Beans partagés, configurés ici et injectés ailleurs (comme dans une vraie application Spring). */
@Configuration
public class LabBeansConfig {

    /** ObjectMapper dangereux : le JSON choisit la classe à instancier. */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        return mapper;
    }

    /** Fabrique XML protégée : DTD interdite. */
    @Bean
    public DocumentBuilderFactory documentBuilderFactory() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        return factory;
    }
}
