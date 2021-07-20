/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Quarto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Christian
 */
@RestController
@RequestMapping("quarto")
public class QuartoController {

    @GetMapping("index")
    public List<Quarto> index() {
        List<Quarto> quartos = new ArrayList<>();
        /* TODO: 
            pegar todos os quartos do banco e adicioná-los na lista
         */
        Quarto quarto = new Quarto();

        quarto.setNumero(100);
        quarto.setTipo("Normal");
        quarto.setVago(true);
        quartos.add(quarto);

        return quartos;
    }

    @GetMapping(value = "index", params = {"numero"})
    public Quarto index(@RequestParam("numero") Integer numero) {
        if (numero != null) {
            Quarto quarto = new Quarto();

            /* TODO:
            Retornar o quarto com o número especificado
             */
            quarto.setNumero(105);
            quarto.setTipo("Normal");
            quarto.setVago(true);

            return quarto;
        } else {
            return null;
        }
    }

    @PostMapping("create")
    public Quarto create(@RequestBody Quarto data) {
        Quarto quarto = new Quarto();

        quarto.setNumero(data.getNumero());
        quarto.setTipo(data.getTipo());
        quarto.setVago(data.isVago());

        /* pegar do banco */
        return quarto;
    }

    @PatchMapping("update")
    public Quarto patch(@RequestBody Quarto data) {
        Integer numero = data.getNumero();

        if (numero != null) {
            /* TODO:
            Retornar o quarto com o número especificado, setar novos atributos e guardar no banco
             */
            Quarto quarto = new Quarto();

            quarto.setNumero(105);
            quarto.setTipo("Normal");
            quarto.setVago(true);

            return quarto;
        } else {
            return null;
        }
    }

    @DeleteMapping("delete")
    public boolean delete(@RequestParam("numero") Integer numero) {
        boolean deleted = false;

        if (numero != null) {
            /* TODO:
                excluir do banco
             */
            deleted = true;
        }

        return deleted;
    }
}
