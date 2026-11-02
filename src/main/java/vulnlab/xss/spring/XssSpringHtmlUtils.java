package vulnlab.xss.spring;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
public class XssSpringHtmlUtils {

    @GetMapping("/xss/greet-safe")
    public void handle(@RequestParam String name, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        response.getWriter().write("<h1>Bonjour " + HtmlUtils.htmlEscape(name) + "</h1>"); // nettoyage
    }
}
