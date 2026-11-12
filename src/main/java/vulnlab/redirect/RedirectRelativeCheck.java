package vulnlab.redirect;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** Vérifie que l'URL est relative (commence par / mais pas par //) avant de rediriger. */
@Controller
public class RedirectRelativeCheck {
    @GetMapping("/redirect/checked")
    public void go(@RequestParam String next, HttpServletResponse response) throws IOException {
        if (!next.startsWith("/") || next.startsWith("//")) {
            next = "/";
        }
        response.sendRedirect(next);
    }
}
