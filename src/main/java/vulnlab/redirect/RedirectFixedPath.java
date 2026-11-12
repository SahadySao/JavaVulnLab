package vulnlab.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** Début de chemin fixe : l'hôte ne peut pas changer. */
@Controller
public class RedirectFixedPath {
    @GetMapping("/redirect/invoice")
    public String go(@RequestParam String id) {
        return "redirect:/factures/" + id;
    }
}
