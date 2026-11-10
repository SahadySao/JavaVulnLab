package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vulnlab.common.InvoiceSearchDto;

/** Le champ du DTO est un Integer : uniquement des chiffres, aucun paramètre ne peut être ajouté. */
@RestController
public class SsrfSpringDtoInteger {
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/ssrf/dtointeger")
    public String handle(@RequestBody InvoiceSearchDto search) {
        return restTemplate.getForObject("https://api.interne/factures?page=" + search.getPage(), String.class);
    }
}
