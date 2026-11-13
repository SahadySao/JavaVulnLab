package vulnlab.bola;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/** L'identifiant passe par un service qui lit la facture sans contrôle. */
@RestController
public class BolaServiceLayer {
    private final DocumentService documentService;

    public BolaServiceLayer(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/bola/documents/{documentId}")
    public Invoice get(@PathVariable Long documentId) {
        return documentService.load(documentId);
    }
}

@Service
class DocumentService {
    private final InvoiceRepository invoiceRepository;

    DocumentService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    Invoice load(Long id) {
        return invoiceRepository.findById(id).orElseThrow();
    }
}
