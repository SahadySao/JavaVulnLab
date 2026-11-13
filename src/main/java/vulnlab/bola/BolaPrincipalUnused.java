package vulnlab.bola;

import java.security.Principal;
import org.springframework.web.bind.annotation.*;

/** L'utilisateur connecté est lu mais jamais comparé au propriétaire. */
@RestController
public class BolaPrincipalUnused {
    private final InvoiceRepository invoiceRepository;
    private String lastReader;

    public BolaPrincipalUnused(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/bola/receipts/{id}")
    public Invoice get(@PathVariable Long id, Principal principal) {
        lastReader = principal.getName();
        return invoiceRepository.findById(id).orElseThrow();
    }
}
