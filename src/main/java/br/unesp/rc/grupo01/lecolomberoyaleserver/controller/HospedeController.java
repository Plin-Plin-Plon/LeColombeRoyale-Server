/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospede;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.HospedeService;
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
@RequestMapping("hospede")
public class HospedeController {

    @Autowired
    private HospedeService service;

    @GetMapping("index")
    public List<Hospede> index() {
        List<Hospede> hospedes = new ArrayList<>();
        hospedes = service.findAll();
        return hospedes;
    }

    @GetMapping(value = "index", params = {"id"})
    public Hospede index(@RequestParam("id") Integer id) {
        if (id != null) {
            Hospede hospede = new Hospede();
            hospede = service.findByIdPessoa(id);
            return hospede;
        } else {
            return null;
        }
    }
    
    @GetMapping(value = "index", params = {"cpf"})
    public Hospede index(@RequestParam("cpf") String cpf) {
        if (cpf != null) {
            Hospede hospede = new Hospede();
            hospede = service.findByCpf(cpf);
            return hospede;
        } else {
            return null;
        }
    }

    @PostMapping("create")
    public Hospede create(@RequestBody Hospede data) {
        Hospede hospede = new Hospede();
        hospede = service.save(data);
        return hospede;
    }

    /*
    @PatchMapping("update")
    public Hospede patch(@RequestBody Hospede data) {
        Hospede hospede = new Hospede();
        hospede = service.update(data);
        return hospede;
    }
*/
    @DeleteMapping("delete")
    @Transactional
    public String delete(@RequestParam("cpf") String cpf) {
        String deleted = "Não encontrado";

        if (cpf != null) {
            deleted = service.deleteByCpf(cpf);
        }

        return deleted;
    }
}
