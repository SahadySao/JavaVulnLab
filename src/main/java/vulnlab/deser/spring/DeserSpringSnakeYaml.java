package vulnlab.deser.spring;

import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.Yaml;

/** SnakeYAML 1.x : le tag !!javax.script.ScriptEngineManager [...] instancie n'importe quelle classe. */
@RestController
public class DeserSpringSnakeYaml {
    @PostMapping("/deser/deserspringsnakeyaml")
    public Object handle(@RequestBody String yaml) {
        return new Yaml().load(yaml);
    }
}
