/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Quarto;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.QuartoService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private QuartoService service;

    @GetMapping("index")
    public List<Quarto> index() {
        List<Quarto> quartos = new ArrayList<>();
        quartos = service.findAll();
        return quartos;
    }

    @GetMapping(value = "index", params = {"numero"})
    public Quarto index(@RequestParam("numero") Integer numero) {
        if (numero != null) {
            Quarto quarto = new Quarto();
            quarto = service.findByNumero(numero);
            return quarto;
        } else {
            return null;
        }
    }

    @PostMapping("create")
    public Quarto create(@RequestBody Quarto data) {
        Quarto quarto = new Quarto();
        quarto = service.save(data);
        return quarto;
    }

    @PatchMapping("update")
    public Quarto patch(@RequestBody Quarto data) {
        Quarto quarto = new Quarto();
        quarto = service.update(data);
        return quarto;
    }

    @DeleteMapping("delete")
    @Transactional
    public int delete(@RequestParam("numero") Integer numero) {
        int deleted = -1;

        if (numero != null) {
            deleted = service.deleteByNumero(numero);
        }

        return deleted;
    }
}
