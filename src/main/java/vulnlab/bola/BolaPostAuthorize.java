package vulnlab.bola;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

/** @PostAuthorize vérifie le propriétaire de l'objet renvoyé. */
@RestController
public class BolaPostAuthorize {
    private final InvoiceRepository invoiceRepository;

    public BolaPostAuthorize(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @PostAuthorize("returnObject.owner == authentication.name")
    @GetMapping("/bola/post-authorized/{id}")
    public Invoice get(@PathVariable Long id) {
        return invoiceRepository.findById(id).orElseThrow();
    }
}
