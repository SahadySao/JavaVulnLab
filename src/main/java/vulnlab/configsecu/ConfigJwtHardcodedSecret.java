package vulnlab.configsecu;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;

/** Secret JWT écrit dans le code. */
public class ConfigJwtHardcodedSecret {
    public Claims read(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor("un-secret-en-dur-de-plus-de-32-octets!!".getBytes(StandardCharsets.UTF_8)))
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
