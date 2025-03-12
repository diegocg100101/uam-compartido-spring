package com.uam.uam_compartido_spring.Service;

import com.uam.uam_compartido_spring.Model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author diego
 */
@Service
public class JwtService {

    private static final String SECRET_KEY = "443f631a53d635c8eb424058fc796199644449588bc927097bd2505884eb808f9534c3309798282faf99d15a30984d1a1eba35075d5b46606cead283c3eeda860e8d452f8166ae5f66ffea4914bdbff3d605cd26fac3c44523ad6e7dba9953f7a5219fd5f4b93042db22d83bd0b5cac6a59ae4d6b04424fd593f6197e720ffed67451b3894cef0c3696206ec4108dc4b08360fe85bf35ce9fe46b0de6bcba81aa33fe8a1e809fdadec5e8c9f6d17d1e2c1044ab38b6572f8ab4a0622b45c4978a32742f8a2262de3fdbdbda3f857373b29062ecb92b6b76192da06ae42db8d0fdb3b56840ad4abec5e44675c30c5b8a924ed9ccbcb6b978dff37dbf0c4234ee2";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Usuario usuario) {
        return generateToken(new HashMap<>(), usuario);
    }

    public String generateToken(Map<String, Object> claims, Usuario usuario) {
        claims.put("role", usuario.getRol().getNombre());
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
