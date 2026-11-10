package vulnlab.pathtraver.spring;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.StoredDocument;
import vulnlab.common.StoredDocumentRepository;

/** Si le document n'existe pas, c'est la valeur de l'UTILISATEUR qui sert de nom : id = "../../etc/passwd". */
@RestController
public class PathSpringLookupOrElse {
    private static final String STORAGE = "/var/app/documents";
    private final StoredDocumentRepository documentRepository;

    public PathSpringLookupOrElse(StoredDocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @GetMapping("/path/pathspringlookuporelse")
    public byte[] handle(@RequestParam String id) throws Exception {
        String name = documentRepository.findById(id).map(StoredDocument::getStoragePath).orElse(id);
        return Files.readAllBytes(Paths.get(STORAGE, name));
    }
}
