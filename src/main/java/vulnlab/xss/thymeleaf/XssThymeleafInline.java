package vulnlab.xss.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** Thymeleaf : [( ... )] écrit sans échappement. */
@Controller
public class XssThymeleafInline {
    @GetMapping("/xss/thymeleaf/inline")
    public String handle(@RequestParam String value, Model model) {
        model.addAttribute("thComment", value);
        return "xss/XssThymeleafInline";
    }
}
