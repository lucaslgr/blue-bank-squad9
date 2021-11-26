package com.squad9.bluebank.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class JwtTokenUtil {
    

    public String gerarToken(String email) throws Exception {
        try {
            String token = JWT.create()
                .withSubject(email)
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

}
