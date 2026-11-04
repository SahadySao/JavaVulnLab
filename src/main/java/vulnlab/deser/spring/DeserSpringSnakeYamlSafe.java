package vulnlab.deser.spring;

import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

/** SafeConstructor : seulement les types YAML standards (map, liste, texte, nombre). */
@RestController
public class DeserSpringSnakeYamlSafe {
    @PostMapping("/deser/deserspringsnakeyamlsafe")
    public Object handle(@RequestBody String yaml) {
        Yaml parser = new Yaml(new SafeConstructor(new LoaderOptions()));
        return parser.load(yaml);
    }
}
