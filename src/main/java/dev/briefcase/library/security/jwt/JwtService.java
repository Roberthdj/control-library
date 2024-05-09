package dev.briefcase.library.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import dev.briefcase.library.security.auth.entity.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${api.secret.key}")
	private String SECRET_KEY;

	public String getToken(AuthUser authUser) {
		return getToken(new HashMap<>(), authUser);
	}

	private String getToken(Map<String, Object> extraClaims, AuthUser authUser) {
		return Jwts.builder()
				.issuer("Api library")
				.subject(authUser.getUsername())
				.claims(extraClaims)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 24 * 3600 * 1000))
				.signWith(getKey())
				.compact();

	}

	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}
	
	private Date getExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}	

	private Boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}	
	
	private Claims getAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);	
	}	

}
