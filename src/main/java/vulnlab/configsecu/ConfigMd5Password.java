package vulnlab.configsecu;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/** Mot de passe haché en MD5, sans sel. */
public class ConfigMd5Password {
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        return HexFormat.of().formatHex(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
    }
}
