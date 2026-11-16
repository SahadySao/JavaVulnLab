package vulnlab.xss.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** Thymeleaf : th:utext écrit l'attribut sans échappement. */
@Controller
public class XssThymeleafUtext {
    @GetMapping("/xss/thymeleaf/utext")
    public String handle(@RequestParam String value, Model model) {
        model.addAttribute("thBio", value);
        return "xss/XssThymeleafUtext";
    }
}
