package vulnlab.bfla;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/** Les autres méthodes exigent ADMIN ; exportAll a été oubliée. */
@RestController
@RequestMapping("/bfla/users")
public class BflaMissingPreAuthorize {
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<String> list() {
        return List.of();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/disable")
    public void disable(@PathVariable long id) {
    }

    @GetMapping("/export")
    public String exportAll() {
        return "id;email;role";
    }
}
