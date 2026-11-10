package vulnlab.xss.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SearchForm;

/** Objet de formulaire implicite (nom : searchForm) ; la vue n'écrit que page, un Integer : sûr. */
@Controller
public class XssJspFormObjectInt {
    @GetMapping("/xss/jsp/formint")
    public String handle(SearchForm searchForm) {
        return "xss/XssJspFormObjectInt";
    }
}
