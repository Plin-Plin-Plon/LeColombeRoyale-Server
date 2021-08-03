/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospede;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.HospedeService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('MOD')")
    @GetMapping("index")
    public ResponseEntity index() {
        List<Hospede> hospedes = new ArrayList<>();
        hospedes = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(hospedes);
    }

    @PreAuthorize("hasRole('MOD')")
    @GetMapping(value = "index", params = {"id"})
    public ResponseEntity index(@RequestParam("id") Integer id) {
        Hospede hospede = new Hospede();
        hospede = service.findByIdPessoa(id);

        if (hospede != null) {
            return ResponseEntity.status(HttpStatus.OK).body(hospede);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hóspede não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping(value = "index", params = {"cpf"})
    public ResponseEntity index(@RequestParam("cpf") String cpf) {
        Hospede hospede = new Hospede();
        hospede = service.findByCpf(cpf);

        if (hospede != null) {
            return ResponseEntity.status(HttpStatus.OK).body(hospede);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hóspede não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PreAuthorize("hasRole('MOD')")
    @PostMapping("create")
    public ResponseEntity create(@RequestBody Hospede data) {
        Hospede hospede = new Hospede();
        hospede = service.save(data);
        return ResponseEntity.status(HttpStatus.OK).body(hospede);
    }

    @PatchMapping("update")
    public ResponseEntity patch(@RequestBody Hospede data) {
        Hospede hospede = new Hospede();
        hospede = service.update(data);
        
        if (hospede != null) {
            return ResponseEntity.status(HttpStatus.OK).body(hospede);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hóspede não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PreAuthorize("hasRole('MOD')")
    @DeleteMapping("delete")
    @Transactional
    public ResponseEntity delete(@RequestParam("cpf") String cpf) {
        int deleted = service.deleteByCpf(cpf);
        Map<String, String> response = new HashMap<>();

        if (deleted >= 1) {
            response.put("message", "Hóspede deletado com sucesso");
        } else {
            response.put("message", "Hóspede não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
