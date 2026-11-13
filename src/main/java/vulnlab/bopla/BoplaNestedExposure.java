package vulnlab.bopla;

import org.springframework.web.bind.annotation.*;

/** Achat renvoyé avec son acheteur : buyer.password part dans le JSON. */
@RestController
public class BoplaNestedExposure {
    private final PurchaseRepository purchaseRepository;

    public BoplaNestedExposure(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @GetMapping("/bopla/purchases/{id}")
    public Purchase get(@PathVariable Long id) {
        return purchaseRepository.findById(id).orElseThrow();
    }
}
