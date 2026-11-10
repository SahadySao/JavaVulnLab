package vulnlab.pathtraver.spring;

import org.springframework.web.bind.annotation.*;

/** Contrôleur du cas PathSpringSingletonField. */
@RestController
public class PathSpringSingletonFieldController {
    private final PathSpringSingletonField service;

    public PathSpringSingletonFieldController(PathSpringSingletonField service) {
        this.service = service;
    }

    @GetMapping("/path/pathspringsingletonfield")
    public String handle(@RequestParam String name) {
        service.prepare(name);
        return service.export().getName();
    }
}
