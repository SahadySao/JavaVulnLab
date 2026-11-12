package vulnlab.xss.jsp;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** model.addAllAttributes(map) : l'attribut "greeting" vient d'un put sur la map. */
@Controller
public class XssJspModelAddAll {
    @GetMapping("/xss/jsp/addall")
    public String handle(@RequestParam String name, Model model) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("greeting", "Bonjour " + name);
        model.addAllAttributes(attributes);
        return "xss/XssJspModelAddAll";
    }
}
