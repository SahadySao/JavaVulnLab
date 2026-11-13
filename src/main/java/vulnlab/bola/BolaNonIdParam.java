package vulnlab.bola;

import java.util.List;
import org.springframework.web.bind.annotation.*;

/** Recherche par titre : pas un identifiant d'objet. */
@RestController
public class BolaNonIdParam {
    private final InvoiceRepository invoiceRepository;

    public BolaNonIdParam(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/bola/search")
    public List<Invoice> search(@RequestParam String title) {
        return invoiceRepository.findByTitle(title);
    }
}
