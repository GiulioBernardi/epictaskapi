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

}
