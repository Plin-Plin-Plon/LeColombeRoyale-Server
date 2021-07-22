/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospede;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Christian
 */
@RestController
@RequestMapping("hospede")
public class HospedeController {

    @GetMapping("index")
    public Hospede index(@RequestParam(value = "id", required = false) int idHospede, @RequestParam(value = "cpf", required = false) String cpf) {
        //String token = getJWTToken(usuario);
        /* pegar do banco */
        return new Hospede();
    }
}
