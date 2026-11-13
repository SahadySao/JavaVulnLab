package vulnlab.bola;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/** Le service compare le propriétaire à l'utilisateur transmis par le contrôleur. */
@RestController
public class BolaServiceOwnerCheck {
    private final StatementService statementService;

    public BolaServiceOwnerCheck(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/bola/statements/{id}")
    public Invoice get(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        return statementService.getForUser(id, user.getUsername());
    }
}

@Service
class StatementService {
    private final InvoiceRepository invoiceRepository;

    StatementService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    Invoice getForUser(Long id, String username) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        if (!invoice.getOwner().equals(username)) {
            throw new AccessDeniedException("relevé d'un autre utilisateur");
        }
        return invoice;
    }
}
