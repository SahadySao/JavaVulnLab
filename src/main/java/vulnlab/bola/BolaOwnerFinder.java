package vulnlab.bola;

import java.security.Principal;
import org.springframework.web.bind.annotation.*;

/** Requête filtrée par le propriétaire. */
@RestController
public class BolaOwnerFinder {
    private final InvoiceRepository invoiceRepository;

    public BolaOwnerFinder(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/bola/my-invoices/{id}")
    public Invoice get(@PathVariable Long id, Principal principal) {
        return invoiceRepository.findByIdAndOwner(id, principal.getName()).orElseThrow();
    }
}
