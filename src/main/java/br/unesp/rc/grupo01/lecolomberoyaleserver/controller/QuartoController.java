/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Quarto;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.QuartoService;
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
@PreAuthorize("hasRole('MOD')")
@RequestMapping("quarto")
public class QuartoController {

    @Autowired
    private QuartoService service;

    @GetMapping("index")
    public ResponseEntity index() {
        List<Quarto> quartos = new ArrayList<>();
        quartos = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(quartos);
    }

    @GetMapping(value = "index", params = {"numero"})
    public ResponseEntity index(@RequestParam("numero") Integer numero) {
        Quarto quarto = new Quarto();
        quarto = service.findByNumero(numero);

        if (quarto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(quarto);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Quarto não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Quarto data) {
        Quarto quarto = new Quarto();
        quarto = service.save(data);

        if (quarto.getNumero() != -1) {
            return ResponseEntity.status(HttpStatus.OK).body(quarto);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Quarto já cadastrado!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PatchMapping("update")
    public ResponseEntity patch(@RequestBody Quarto data) {
        Quarto quarto = new Quarto();
        quarto = service.update(data);

        if (quarto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(quarto);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Quarto não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @DeleteMapping("delete")
    @Transactional
    public ResponseEntity delete(@RequestParam("numero") Integer numero) {
        int deleted = service.deleteByNumero(numero);
        Map<String, String> response = new HashMap<>();

        if (deleted >= 1) {
            response.put("message", "Quarto de número " + numero + " deletado com sucesso");
        } else {
            response.put("message", "Quarto de número " + numero + " não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
