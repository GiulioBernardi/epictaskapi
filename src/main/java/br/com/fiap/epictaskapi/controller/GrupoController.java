package br.com.fiap.epictaskapi.controller;

import br.com.fiap.epictaskapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    TokenService service;

    @GetMapping
    public String getGrupo(){
        return "Andre Menezes - 88112\nGiulio Bernardi - 86993\nKaike de Santana - 88473\nRaissa Rassilan - 89011";
    }
}
