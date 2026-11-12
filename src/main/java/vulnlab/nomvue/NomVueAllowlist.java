package vulnlab.nomvue;

import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** Liste blanche des sections : sûr. */
@Controller
public class NomVueAllowlist {
    private static final Set<String> SECTIONS = Set.of("contact", "faq", "mentions");

    @GetMapping("/nomvue/allowlist")
    public String show(@RequestParam String section) {
        if (!SECTIONS.contains(section)) {
            return "error";
        }
        return "help/" + section;
    }
}
