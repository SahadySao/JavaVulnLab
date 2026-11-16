package vulnlab.xss.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vulnlab.lombok.LombokSearchForm;

/** Même objet Lombok, mais la JSP n'écrit que ${lombokSearchPage.page} (Integer) : sûr. */
@Controller
public class XssJspFormObjectLombokInt {
    @GetMapping("/xss/jsp/formlombokint")
    public String handle(@ModelAttribute("lombokSearchPage") LombokSearchForm search) {
        return "xss/XssJspFormObjectLombokInt";
    }
}
