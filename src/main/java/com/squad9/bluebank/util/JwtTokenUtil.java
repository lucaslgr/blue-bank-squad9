package com.squad9.bluebank.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    public String gerarToken(String email, Long idCliente) throws Exception {
        try {
            String token = JWT.create()
                    .withSubject(email)
                    .withClaim("idCliente", idCliente)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .sign(getSigner());
            return token;
        } catch (JWTCreationException e) {
            throw new Exception("Não foi possivel gerar JWT token");
        }
    }

    public Algorithm getSigner() {
        // todo: usar uma chave real usango environment variables
        Algorithm algorithm = Algorithm.HMAC256("secret");
        return algorithm;
    }

    public String getEmailDoToken(String token) {
        String email = JWT.require(getSigner()).build().verify(token).getSubject();
        return email;
    }

    public boolean isTokenValido(String token) {
        try {
            JWT.require(getSigner()).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
