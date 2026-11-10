package vulnlab.pathtraver.spring;

import java.io.File;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

/** La clé choisit l'entrée d'une table fixe ; le chemin vient de la table (second ordre au plus). */
@RestController
public class PathSpringTableLookup {
    private static final Map<String, String> TEMPLATES = Map.of(
        "invoice", "/var/app/templates/invoice.pdf",
        "quote", "/var/app/templates/quote.pdf");

    @GetMapping("/path/tablelookup")
    public long handle(@RequestParam String kind) {
        String path = TEMPLATES.get(kind);
        return new File(path).length();
    }
}
