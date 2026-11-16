package vulnlab.xss.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** Thymeleaf : th:utext sur #strings.escapeXml(...) : sûr. */
@Controller
public class XssThymeleafEscaped {
    @GetMapping("/xss/thymeleaf/escaped")
    public String handle(@RequestParam String value, Model model) {
        model.addAttribute("thBioEscaped", value);
        return "xss/XssThymeleafEscaped";
    }
}
