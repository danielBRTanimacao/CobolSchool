package CobolSchool.service;

import CobolSchool.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.springframework.security.config.Elements.JWT;

@Slf4j
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserEntity user) {
        try {
            log.debug("Generating token for user: {}", user.getEmail());
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            log.error("Error while generating token for user: {}", user.getEmail(), e);
            throw new TokenException("Error while authenticating");
        }
    }

    public String validateTokens(String token){
        try {
            log.debug("Validating token: {}", token);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            log.error("Error while validating token: {}", e.getMessage());
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(23).toInstant(ZoneOffset.of("-03:00"));
    }
}
