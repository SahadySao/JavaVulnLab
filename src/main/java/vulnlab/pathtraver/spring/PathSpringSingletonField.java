package vulnlab.pathtraver.spring;

import java.io.File;
import org.springframework.stereotype.Service;

/** Singleton qui garde une donnée de l'utilisateur dans un champ (déjà un bug : partagé entre tous). */
@Service
public class PathSpringSingletonField {
    private static final String EXPORT_DIR = "/var/app/exports";
    private String lastFileName;

    public void prepare(String name) {
        this.lastFileName = name;
    }

    public File export() {
        return new File(EXPORT_DIR, this.lastFileName);
    }
}
