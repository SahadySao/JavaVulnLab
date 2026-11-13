package vulnlab.bfla;

import org.springframework.web.bind.annotation.*;

/** Suppression d'utilisateur sous /bfla/admin/** : rôle ADMIN exigé par la règle d'URL. */
@RestController
public class BflaAdminByUrl {
    @DeleteMapping("/bfla/admin/users/{id}")
    public void deleteUser(@PathVariable long id) {
    }
}
