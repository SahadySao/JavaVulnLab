package vulnlab.pathtraver.spring;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

/** Ressource du classpath : name = "../application.properties" sort du dossier des modèles. */
@RestController
public class PathSpringClassPathResource {
    @GetMapping("/path/classpathresource")
    public byte[] handle(@RequestParam String name) throws Exception {
        return new ClassPathResource("templates/" + name).getInputStream().readAllBytes();
    }
}
