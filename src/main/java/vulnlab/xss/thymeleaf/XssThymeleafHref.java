package vulnlab.xss.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** Thymeleaf : th:href avec l'URL entière : javascript:alert(1) passe. */
@Controller
public class XssThymeleafHref {
    @GetMapping("/xss/thymeleaf/href")
    public String handle(@RequestParam String value, Model model) {
        model.addAttribute("thSite", value);
        return "xss/XssThymeleafHref";
    }
}
