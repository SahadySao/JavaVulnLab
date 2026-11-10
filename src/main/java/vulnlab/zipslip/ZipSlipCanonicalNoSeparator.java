package vulnlab.zipslip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/** Variante connue : comparaison sans séparateur, /dest-evil/x passe le contrôle. */
public class ZipSlipCanonicalNoSeparator {
    public void extract(InputStream archive, File destDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(archive)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File out = new File(destDir, entry.getName());
                if (!out.getCanonicalPath().startsWith(destDir.getCanonicalPath())) {
                    throw new IOException("Entrée hors du dossier");
                }
                try (FileOutputStream fos = new FileOutputStream(out)) {
                    zis.transferTo(fos);
                }
            }
        }
    }
}
