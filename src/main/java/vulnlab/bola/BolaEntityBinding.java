package vulnlab.bola;

import org.springframework.web.bind.annotation.*;

/** Spring charge l'entité à partir de l'identifiant du chemin (DomainClassConverter), sans contrôle. */
@RestController
public class BolaEntityBinding {
    @GetMapping("/bola/notes/{id}")
    public Invoice view(@PathVariable("id") Invoice invoice) {
        return invoice;
    }
}
