/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Servico;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.ServicoService;
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
@RequestMapping("servico")
public class ServicoController {
    
    @Autowired
    private ServicoService service;

    @GetMapping("index")
    public List<Servico> index() {
        List<Servico> servicos = new ArrayList<>();
        servicos = service.findAll();
        return servicos;
    }

    @GetMapping(value = "index", params = {"id"})
    public Servico index(@RequestParam("id") Integer idServico) {
        if (idServico != null) {
            Servico servico = new Servico();
            servico = service.findByIdServico(idServico);
            return servico;
        } else {
            return null;
        }
    }

    @PostMapping("create")
    public Servico create(@RequestBody Servico data) {
        Servico servico = new Servico();
        servico = service.save(data);
        return servico;
    }

    @PatchMapping("update")
    public Servico patch(@RequestBody Servico data) {
        Servico servico = new Servico();
        servico = service.update(data);
        return servico;
    }

    @DeleteMapping("delete")
    @Transactional
    public int delete(@RequestParam("id") Integer idServico) {
        int deleted = -1;

        if (idServico != null) {
            deleted = service.deleteByIdServico(idServico);
        }

        return deleted;
    }
}
