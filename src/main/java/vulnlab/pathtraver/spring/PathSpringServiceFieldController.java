package vulnlab.pathtraver.spring;

import org.springframework.web.bind.annotation.*;

/** Contrôleur du cas PathSpringServiceField (le sink est dans le service). */
@RestController
public class PathSpringServiceFieldController {
    private final PathSpringServiceField service;

    public PathSpringServiceFieldController(PathSpringServiceField service) {
        this.service = service;
    }

    @GetMapping("/path/pathspringservicefield")
    public String handle(@RequestParam String name) {
        return service.report(name).getName();
    }
}
