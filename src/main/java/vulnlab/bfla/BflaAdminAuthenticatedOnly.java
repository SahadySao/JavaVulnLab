package vulnlab.bfla;

import org.springframework.web.bind.annotation.*;

/** Attribution de rôle hors de /bfla/admin/** : n'importe quel utilisateur connecté peut l'appeler. */
@RestController
@RequestMapping("/bfla/management")
public class BflaAdminAuthenticatedOnly {
    @PostMapping("/roles")
    public void grantRole(@RequestParam String user, @RequestParam String role) {
    }
}
