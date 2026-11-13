package vulnlab.bola;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/** Suppression par EntityManager à partir de l'identifiant du chemin. */
@RestController
public class BolaEntityManagerDelete {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @DeleteMapping("/bola/orders/{orderId}")
    public void delete(@PathVariable Long orderId) {
        Invoice invoice = em.find(Invoice.class, orderId);
        em.remove(invoice);
    }
}
