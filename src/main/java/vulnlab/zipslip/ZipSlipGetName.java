package vulnlab.zipslip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.FilenameUtils;

/** On ne garde que le nom du fichier : l'arborescence de l'archive est aplatie. */
public class ZipSlipGetName {
    public void extract(InputStream archive, File destDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(archive)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File out = new File(destDir, FilenameUtils.getName(entry.getName()));
                try (FileOutputStream fos = new FileOutputStream(out)) {
                    zis.transferTo(fos);
                }
            }
        }
    }
}
