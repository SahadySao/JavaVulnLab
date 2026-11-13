package vulnlab.bola;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/** ACL : hasPermission sur l'identifiant. */
@RestController
public class BolaHasPermission {
    private final InvoiceRepository invoiceRepository;

    public BolaHasPermission(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @PreAuthorize("hasPermission(#id, 'Invoice', 'read')")
    @GetMapping("/bola/acl/{id}")
    public Invoice get(@PathVariable Long id) {
        return invoiceRepository.findById(id).orElseThrow();
    }
}
