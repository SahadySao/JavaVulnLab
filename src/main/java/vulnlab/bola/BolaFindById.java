package vulnlab.bola;

import org.springframework.web.bind.annotation.*;

/** Facture lue par son identifiant, sans vérifier à qui elle appartient. */
@RestController
public class BolaFindById {
    private final InvoiceRepository invoiceRepository;

    public BolaFindById(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/bola/invoices/{id}")
    public Invoice get(@PathVariable Long id) {
        return invoiceRepository.findById(id).orElseThrow();
    }
}
