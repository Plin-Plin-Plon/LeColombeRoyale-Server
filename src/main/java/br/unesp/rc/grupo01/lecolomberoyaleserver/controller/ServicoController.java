/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Servico;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.ServicoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity index() {
        List<Servico> servicos = new ArrayList<>();
        servicos = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(servicos);
    }

    @GetMapping(value = "index", params = {"id"})
    public ResponseEntity index(@RequestParam("id") Integer idServico) {
        Servico servico = new Servico();
        servico = service.findByIdServico(idServico);

        if (servico != null) {
            return ResponseEntity.status(HttpStatus.OK).body(servico);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Serviço não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Servico data) {
        Servico servico = new Servico();
        servico = service.save(data);
        return ResponseEntity.status(HttpStatus.OK).body(servico);
    }

    @PatchMapping("update")
    public ResponseEntity patch(@RequestBody Servico data) {
        Servico servico = new Servico();
        servico = service.update(data);

        if (servico != null) {
            return ResponseEntity.status(HttpStatus.OK).body(servico);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Serviço não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @DeleteMapping("delete")
    @Transactional
    public ResponseEntity delete(@RequestParam("id") Integer idServico) {
        int deleted = service.deleteByIdServico(idServico);
        Map<String, String> response = new HashMap<>();

        if (deleted >= 1) {
            response.put("message", "Serviço de id " + idServico + " deletado com sucesso");
        } else {
            response.put("message", "Serviço de id " + idServico + " não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
