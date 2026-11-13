package vulnlab.bola;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository des cas bola (pas un cas). */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByIdAndOwner(Long id, String owner);
    List<Invoice> findByTitle(String title);
}
