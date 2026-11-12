package vulnlab.redirect;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

/** En-tête Location écrit à la main. */
@RestController
public class RedirectLocationHeader {
    @GetMapping("/redirect/header")
    public void go(@RequestParam String url, HttpServletResponse response) {
        response.setStatus(302);
        response.setHeader("Location", url);
    }
}
