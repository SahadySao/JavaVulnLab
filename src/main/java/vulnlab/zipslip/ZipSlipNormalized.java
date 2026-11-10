package vulnlab.zipslip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/** Chemin normalisé puis vérifié : la correction recommandée. */
public class ZipSlipNormalized {
    public void extract(InputStream archive, Path destDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(archive)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                Path target = destDir.resolve(entry.getName()).normalize();
                if (!target.startsWith(destDir)) {
                    throw new IOException("Entrée hors du dossier : " + entry.getName());
                }
                Files.copy(zis, target);
            }
        }
    }
}
