package vulnlab.bola;

import java.security.Principal;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

/** Facture lue puis propriétaire comparé à l'utilisateur connecté. */
@RestController
public class BolaOwnerCompared {
    private final InvoiceRepository invoiceRepository;

    public BolaOwnerCompared(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/bola/checked-invoices/{id}")
    public Invoice get(@PathVariable Long id, Principal principal) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        if (!invoice.getOwner().equals(principal.getName())) {
            throw new AccessDeniedException("facture d'un autre utilisateur");
        }
        return invoice;
    }
}
