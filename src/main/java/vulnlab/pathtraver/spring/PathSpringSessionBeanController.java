package vulnlab.pathtraver.spring;

import org.springframework.web.bind.annotation.*;

/** Contrôleur du cas PathSpringSessionBean. */
@RestController
public class PathSpringSessionBeanController {
    private final PathSpringSessionBean session;

    public PathSpringSessionBeanController(PathSpringSessionBean session) {
        this.session = session;
    }

    @GetMapping("/path/pathspringsessionbean")
    public String handle(@RequestParam String name) {
        return session.exportFor(name).getName();
    }
}
