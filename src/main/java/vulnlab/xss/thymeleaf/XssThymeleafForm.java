package vulnlab.xss.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SortDto;

/** Thymeleaf : objet de formulaire réaffiché avec th:utext="*{free}" dans th:object="${sortForm}". */
@Controller
public class XssThymeleafForm {
    @PostMapping("/xss/thymeleaf/form")
    public String handle(@ModelAttribute("sortForm") SortDto sort) {
        return "xss/XssThymeleafForm";
    }
}
