package org.example.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.example.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private static final String TOKEN_TYPE_CLAIM = "token_type";
    private static final String ACCESS_TOKEN_TYPE = "access_token";
    private static final String REFRESH_TOKEN_TYPE = "refresh_token";

    @Value("${ums.app.jwtSecret}")
    private String jwtSecret;
    @Value("${ums.app.jwtExpirationMs}")
    private int jwtExpirationMs;
    @Value("${ums.app.jwtRefreshExpirationMs}")
    private int jwtRefreshExpirationMs;


    public String generateJwt(String username) {
        return generateTokenFromPseudo(username, ACCESS_TOKEN_TYPE, jwtExpirationMs);
    }

    public String generateRefreshTokenFromPseudo(String username) {
        return generateTokenFromPseudo(username, REFRESH_TOKEN_TYPE, jwtRefreshExpirationMs);
    }

    private String generateTokenFromPseudo(String pseudo, String tokenType, int expiration) {
        return Jwts.builder()
                .setSubject(pseudo)
                .claim(TOKEN_TYPE_CLAIM, tokenType) // Add token type claim
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new ApplicationException("unAuthenticated");
        }
    }

    public boolean isAccessToken(String token) {
        return isTokenOfType(token, ACCESS_TOKEN_TYPE);
    }

    public boolean isRefreshToken(String token) {
        return isTokenOfType(token, REFRESH_TOKEN_TYPE);
    }

    private boolean isTokenOfType(String token, String tokenType) {
        try {
            String actualTokenType = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody()
                    .get(TOKEN_TYPE_CLAIM, String.class);
            return actualTokenType != null && actualTokenType.equals(tokenType);
        } catch (Exception e) {
            throw new ApplicationException("unAuthenticated");
        }
    }

}
