package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vulnlab.common.InvoiceSearchDto;

/** Le champ du DTO est un String : customer = "x&admin=true" ajoute un paramètre. */
@RestController
public class SsrfSpringDtoString {
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/ssrf/dtostring")
    public String handle(@RequestBody InvoiceSearchDto search) {
        return restTemplate.getForObject("https://api.interne/factures?client=" + search.getCustomer(), String.class);
    }
}
