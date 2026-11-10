package vulnlab.xss.spring;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.web.bind.annotation.*;

/** Jsoup.clean : le HTML est gardé, mais filtré par une liste blanche de balises. */
@RestController
public class XssSpringJsoupClean {
    @PostMapping("/xss/jsoupclean")
    public void handle(@RequestParam String comment, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().print("<div>" + Jsoup.clean(comment, Safelist.basic()) + "</div>");
    }
}
