package vulnlab.nomvue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** "redirect:" + x : redirection (règle redirect), pas un nom de vue. */
@Controller
public class NomVueRedirect {
    @GetMapping("/nomvue/redirect")
    public String show(@RequestParam String back) {
        return "redirect:/accueil?retour=" + back;
    }
}
