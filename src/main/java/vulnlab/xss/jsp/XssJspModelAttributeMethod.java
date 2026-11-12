package vulnlab.xss.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** @ModelAttribute sur une méthode : sa valeur est ajoutée au modèle de tous les gestionnaires du contrôleur. */
@Controller
public class XssJspModelAttributeMethod {
    @ModelAttribute("lastSearch")
    public String lastSearch(@RequestParam(required = false) String q) {
        return q;
    }

    @GetMapping("/xss/jsp/modelattributemethod")
    public String handle() {
        return "xss/XssJspModelAttributeMethod";
    }
}
