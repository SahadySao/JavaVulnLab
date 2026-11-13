package vulnlab.configsecu;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/** SHA-256 pour l'empreinte d'un fichier : usage légitime, pas un mot de passe. */
public class ConfigSha256Checksum {
    public String checksum(byte[] content) throws NoSuchAlgorithmException {
        return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(content));
    }
}
