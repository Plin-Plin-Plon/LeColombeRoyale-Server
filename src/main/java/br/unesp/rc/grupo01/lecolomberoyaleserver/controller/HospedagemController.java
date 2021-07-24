/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospedagem;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.HospedagemService;
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
@RequestMapping("hospedagem")
public class HospedagemController {
    
    @Autowired
    private HospedagemService service;

    @GetMapping("index")
    public List<Hospedagem> index() {
        List<Hospedagem> hospedagens = new ArrayList<>();
        hospedagens = service.findAll();
        return hospedagens;
    }

    @GetMapping(value = "index", params = {"id"})
    public Hospedagem index(@RequestParam("id") Integer idHospedagem) {
        if (idHospedagem != null) {
            Hospedagem hospedagem = new Hospedagem();
            hospedagem = service.findByIdHospedagem(idHospedagem);
            return hospedagem;
        } else {
            return null;
        }
    }

    @PostMapping("create")
    public Hospedagem create(@RequestBody Hospedagem data) {
        Hospedagem hospedagem = new Hospedagem();
        hospedagem = service.save(data);
        return hospedagem;
    }

    @PatchMapping("update")
    public Hospedagem patch(@RequestBody Hospedagem data) {
        Hospedagem hospedagem = new Hospedagem();
        hospedagem = service.update(data);
        return hospedagem;
    }

    @DeleteMapping("delete")
    @Transactional
    public int delete(@RequestParam("id") Integer idHospedagem) {
        int deleted = -1;

        if (idHospedagem != null) {
            deleted = service.deleteByIdHospedagem(idHospedagem);
        }

        return deleted;
    }
}
