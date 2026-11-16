package vulnlab.bola;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/** L'utilisateur connecté n'est lu que pour le journal ; la seule comparaison porte sur le titre. */
@RestController
public class BolaComparedUnrelated {
    private static final Logger log = LoggerFactory.getLogger(BolaComparedUnrelated.class);
    private final InvoiceRepository invoiceRepository;

    public BolaComparedUnrelated(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/bola/archived-invoices/{id}")
    public Invoice get(@PathVariable Long id, Principal principal) {
        log.info("Lecture de la facture {} par {}", id, principal.getName());
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        if (invoice.getTitle().equals("brouillon")) {
            throw new IllegalStateException("facture non publiée");
        }
        return invoice;
    }
}
