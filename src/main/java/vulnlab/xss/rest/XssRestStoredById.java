package vulnlab.xss.rest;

import org.springframework.web.bind.annotation.*;
import vulnlab.bola.Invoice;
import vulnlab.bola.InvoiceRepository;

/** Second ordre (XSS stocké) : l'identifiant est un nombre, mais le TITRE lu en base est écrit sans échappement.
  * Rubrique secondOrdre du rapport xss (pas compté par expected.csv). */
@RestController
public class XssRestStoredById {
    private final InvoiceRepository invoiceRepository;

    public XssRestStoredById(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/xss/rest/stored/{id}")
    public String show(@PathVariable Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        return "<h1>" + invoice.getTitle() + "</h1>";
    }

    /** Même lecture, mais seul l'identifiant (un nombre) est écrit : rien à signaler. */
    @GetMapping("/xss/rest/storedid/{id}")
    public String showId(@PathVariable Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        return "<h1>Facture " + invoice.getId() + "</h1>";
    }
}
