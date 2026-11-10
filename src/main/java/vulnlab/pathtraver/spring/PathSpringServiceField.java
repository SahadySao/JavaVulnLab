package vulnlab.pathtraver.spring;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vulnlab.common.Notifier;

/** Le nom de l'utilisateur part dans un appel inconnu ; le chemin vient de la configuration. */
@Service
public class PathSpringServiceField {
    @Value("${app.reports-dir}")
    private String reportsDir;
    private final Notifier notifier;

    public PathSpringServiceField(Notifier notifier) {
        this.notifier = notifier;
    }

    public File report(String name) {
        notifier.notify("Rapport demandé par " + name);
        return new File(reportsDir, "rapport.pdf");
    }
}
