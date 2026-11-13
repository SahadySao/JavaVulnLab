package vulnlab.bola;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/** Vérification explicite de propriété avant la lecture. */
@RestController
public class BolaExplicitCheck {
    private final InvoiceRepository invoiceRepository;
    private final OwnershipService ownershipService;

    public BolaExplicitCheck(InvoiceRepository invoiceRepository, OwnershipService ownershipService) {
        this.invoiceRepository = invoiceRepository;
        this.ownershipService = ownershipService;
    }

    @GetMapping("/bola/verified/{id}")
    public Invoice get(@PathVariable Long id) {
        ownershipService.checkOwnership(id);
        return invoiceRepository.findById(id).orElseThrow();
    }
}

@Service
class OwnershipService {
    void checkOwnership(Long id) {
    }
}
