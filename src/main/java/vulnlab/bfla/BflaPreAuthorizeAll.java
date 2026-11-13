package vulnlab.bfla;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/** Toutes les méthodes exigent un rôle. */
@RestController
@RequestMapping("/bfla/accounts")
public class BflaPreAuthorizeAll {
    @PreAuthorize("hasRole('SUPPORT')")
    @PostMapping("/{id}/unlock")
    public void unlock(@PathVariable long id) {
    }

    @PreAuthorize("hasAuthority('ACCOUNT_DELETE')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
    }
}
