package vulnlab.xss.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SearchForm;

/** @ModelAttribute : Spring met l'objet du formulaire dans le modèle ; la vue écrit ${search.keyword}. */
@Controller
public class XssJspFormObject {
    @GetMapping("/xss/jsp/form")
    public String handle(@ModelAttribute("search") SearchForm search) {
        return "xss/XssJspFormObject";
    }
}
