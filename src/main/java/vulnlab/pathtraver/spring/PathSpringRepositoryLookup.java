package vulnlab.pathtraver.spring;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.StoredDocument;
import vulnlab.common.StoredDocumentRepository;

/** L'utilisateur choisit QUEL document est lu ; le chemin vient de la base, pas de lui. */
@RestController
public class PathSpringRepositoryLookup {
    private static final String STORAGE = "/var/app/documents";
    private final StoredDocumentRepository documentRepository;

    public PathSpringRepositoryLookup(StoredDocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @GetMapping("/path/pathspringrepositorylookup")
    public byte[] handle(@RequestParam String id) throws Exception {
        StoredDocument doc = documentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Document introuvable : " + id));
        return Files.readAllBytes(Paths.get(STORAGE, doc.getStoragePath()));
    }
}
