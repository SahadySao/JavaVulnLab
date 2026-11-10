package vulnlab.ssrf.spring;

import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.*;

/** Jsoup.connect : récupération du titre d'une page (aperçu de lien). */
@RestController
public class SsrfSpringJsoup {
    @GetMapping("/ssrf/ssrfspringjsoup")
    public String handle(@RequestParam String link) throws Exception {
        return Jsoup.connect(link).get().title();
    }
}
