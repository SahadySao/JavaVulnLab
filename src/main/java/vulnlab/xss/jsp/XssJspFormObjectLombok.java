package vulnlab.xss.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vulnlab.lombok.LombokSearchForm;

/** Objet de formulaire Lombok (@Getter @Setter) réaffiché par la JSP avec ${lombokSearch.keyword}. */
@Controller
public class XssJspFormObjectLombok {
    @GetMapping("/xss/jsp/formlombok")
    public String handle(@ModelAttribute("lombokSearch") LombokSearchForm search) {
        return "xss/XssJspFormObjectLombok";
    }
}
