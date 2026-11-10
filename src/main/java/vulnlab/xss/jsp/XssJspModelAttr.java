package vulnlab.xss.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** Le contrôleur donne le paramètre à la vue, qui l'écrit avec ${message} (non échappé). */
@Controller
public class XssJspModelAttr {
    @GetMapping("/xss/jsp/modelattr")
    public String handle(@RequestParam String message, Model model) {
        model.addAttribute("message", "Bienvenue " + message);
        return "xss/XssJspModelAttr";
    }
}
