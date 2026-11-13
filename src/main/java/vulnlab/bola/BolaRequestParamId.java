package vulnlab.bola;

import org.springframework.web.bind.annotation.*;

/** Identifiant passé en paramètre de requête. */
@RestController
public class BolaRequestParamId {
    private final InvoiceRepository invoiceRepository;

    public BolaRequestParamId(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/bola/invoice")
    public Invoice get(@RequestParam("invoiceId") Long invoiceId) {
        return invoiceRepository.getReferenceById(invoiceId);
    }
}
