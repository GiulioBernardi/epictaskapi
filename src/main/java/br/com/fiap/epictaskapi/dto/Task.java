package br.com.fiap.epictaskapi.dto;


import javax.persistence.Entity;
import java.util.Objects;

public record Task (

        String titulo,
        String descricao,
        int pontuacao,
        int status
) {
}
