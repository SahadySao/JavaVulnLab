package vulnlab.xss.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** Même chose, mais la vue utilise c:out : échappé. */
@Controller
public class XssJspModelAttrCOut {
    @GetMapping("/xss/jsp/modelattrcout")
    public String handle(@RequestParam String notice, Model model) {
        model.addAttribute("notice", notice);
        return "xss/XssJspModelAttrCOut";
    }
}
