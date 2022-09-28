package br.com.fiap.epictaskapi.service;

import br.com.fiap.epictaskapi.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {

    public String createToken(User user){
        Date dataDeGeracao = new Date();
        Date dataDeExpiracao = new Date(dataDeGeracao.getTime() + 60_000);
        return JWT.create()
                .withSubject(user.getEmail())
                .withIssuedAt(dataDeGeracao)
                .withExpiresAt(dataDeExpiracao)
                .sign(Algorithm.HMAC512("secret"));
    }

    public boolean valide(String header) {
        String token = header.substring(7);
        if(header == null || !header.startsWith("Bearer ") || header.isEmpty()) return false;

        try{
            JWT.require(Algorithm.HMAC512("secret")).build().verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
