package br.com.fiap.epictaskapi.config.security;

import antlr.Token;
import br.com.fiap.epictaskapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if(new TokenService().valide(header)){
            System.out.println("Autenticado");
        }else{
            System.out.println("Não autenticado");
        }
        filterChain.doFilter(request, response);
    }
}