package vulnlab.xss.spring;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class XssSpringResponseWriter {

    @GetMapping("/xss/greet")
    public void handle(@RequestParam String name, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        response.getWriter().write("<h1>Bonjour " + name + "</h1>");
    }
}
