package vulnlab.common;

import org.springframework.data.jpa.repository.JpaRepository;

/** Repository Spring Data : findById est généré par Spring, Joern n'en connaît pas le code. */
public interface StoredDocumentRepository extends JpaRepository<StoredDocument, String> {
}
