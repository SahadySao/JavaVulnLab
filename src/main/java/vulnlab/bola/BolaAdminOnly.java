package vulnlab.bola;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/** Consultation réservée aux administrateurs : toutes les factures, par conception. */
@RestController
public class BolaAdminOnly {
    private final InvoiceRepository invoiceRepository;

    public BolaAdminOnly(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/bola/support/invoices/{id}")
    public Invoice get(@PathVariable Long id) {
        return invoiceRepository.findById(id).orElseThrow();
    }
}
