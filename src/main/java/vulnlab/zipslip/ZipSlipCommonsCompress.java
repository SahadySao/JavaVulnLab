package vulnlab.zipslip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

/** Archive tar (Commons Compress), même défaut. */
public class ZipSlipCommonsCompress {
    public void extract(InputStream archive, String destDir) throws IOException {
        try (TarArchiveInputStream tar = new TarArchiveInputStream(archive)) {
            TarArchiveEntry entry;
            while ((entry = tar.getNextEntry()) != null) {
                Files.copy(tar, Paths.get(destDir, entry.getName()));
            }
        }
    }
}
