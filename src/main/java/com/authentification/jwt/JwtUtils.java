package com.authentification.jwt;
import java.util.Date;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.authentification.service.UserDetailsImpl;

@Component
	public class JwtUtils {
		private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

		@Value("wajdiSecretKey")
		private String jwtSecret;

		@Value("86400000000")
		private Long jwtExpirationMs;

		public String generateJwtToken(Authentication authentication) {

			UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

			return Jwts.builder()
					.setSubject((userPrincipal.getUsername()))
					.claim("id",userPrincipal.getId())
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
					.signWith(SignatureAlgorithm.HS512, jwtSecret)
					.compact();
		}

		public String getUserNameFromJwtToken(String token) {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		}

	public void invalidateJwtToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (ExpiredJwtException e) {
			// Token is already expired. Do nothing.
		} catch (JwtException e) {
			throw new BadCredentialsException("Invalid JWT token: " + e.getMessage());
		}
	}


	public boolean validateJwtToken(String authToken) {
			try {
				Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
				return true;
			} catch (SignatureException e) {
				logger.error("Invalid JWT signature: {}", e.getMessage());
			} catch (MalformedJwtException e) {
				logger.error("Invalid JWT token: {}", e.getMessage());
			} catch (ExpiredJwtException e) {
				logger.error("JWT token is expired: {}", e.getMessage());
			} catch (UnsupportedJwtException e) {
				logger.error("JWT token is unsupported: {}", e.getMessage());
			} catch (IllegalArgumentException e) {
				logger.error("JWT claims string is empty: {}", e.getMessage());
			}

			return false;
		}

	public Long getUserIdFromToken(String token) {

		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return  Long.valueOf((Integer) claims.get("id"));
	}

	}
