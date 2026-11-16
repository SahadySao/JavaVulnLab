package vulnlab.xss.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** Thymeleaf : th:text échappe : sûr. */
@Controller
public class XssThymeleafText {
    @GetMapping("/xss/thymeleaf/text")
    public String handle(@RequestParam String value, Model model) {
        model.addAttribute("thBioText", value);
        return "xss/XssThymeleafText";
    }
}
