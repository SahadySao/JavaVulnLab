package vulnlab.bola;

import org.springframework.web.bind.annotation.*;

/** Requête filtrée par un propriétaire... choisi par l'utilisateur : ownerId=autre-client. */
@RestController
public class BolaOwnerFromRequest {
    private final InvoiceRepository invoiceRepository;

    public BolaOwnerFromRequest(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/bola/customer-invoices/{id}")
    public Invoice get(@PathVariable Long id, @RequestParam String ownerId) {
        return invoiceRepository.findByIdAndOwner(id, ownerId).orElseThrow();
    }
}
