package vulnlab.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** "redirect:" + url */
@Controller
public class RedirectSpringView {
    @PostMapping("/redirect/login")
    public String login(@RequestParam String next) {
        return "redirect:" + next;
    }
}
