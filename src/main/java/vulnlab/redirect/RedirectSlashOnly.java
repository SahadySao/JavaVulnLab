package vulnlab.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** "redirect:/" + path : path = /pirate.fr donne //pirate.fr (URL relative au protocole). */
@Controller
public class RedirectSlashOnly {
    @GetMapping("/redirect/slash")
    public String go(@RequestParam String path) {
        return "redirect:/" + path;
    }
}
