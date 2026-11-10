package vulnlab.pathtraver.spring;

import java.io.File;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/** Bean de SESSION : ses champs contiennent des données de l'utilisateur, ce n'est pas un singleton. */
@Component
@SessionScope
public class PathSpringSessionBean {
    private static final String EXPORT_DIR = "/var/app/exports";
    private String fileName;

    public File exportFor(String name) {
        this.fileName = name;
        return new File(EXPORT_DIR, this.fileName);
    }
}
