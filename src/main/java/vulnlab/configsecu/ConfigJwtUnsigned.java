package vulnlab.configsecu;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

/** Jeton lu sans vérifier sa signature. */
public class ConfigJwtUnsigned {
    public Claims read(String token) {
        Jwt<?, Claims> jwt = Jwts.parserBuilder().build().parseClaimsJwt(token);
        return jwt.getBody();
    }
}
