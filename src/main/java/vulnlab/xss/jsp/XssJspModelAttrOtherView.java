package vulnlab.xss.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** L'attribut "otherMessage" est donné à la vue XssJspModelAttrOtherViewTarget, qui ne l'écrit pas.
  * Une AUTRE JSP (XssJspModelAttrOtherView.jsp) écrit ${otherMessage} : la vue renvoyée étant connue,
  * il ne faut pas les relier. */
@Controller
public class XssJspModelAttrOtherView {
    @PostMapping("/xss/jsp/otherview")
    public String handle(@RequestParam String message, Model model) {
        model.addAttribute("otherMessage", message);
        return "xss/XssJspModelAttrOtherViewTarget";
    }
}
