package br.com.fiap.epictaskapi.dto;


public record Task (

        String titulo,
        String descricao,
        int pontuacao,
        int status
) {
}
