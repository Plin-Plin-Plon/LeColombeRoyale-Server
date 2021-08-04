/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospedagem;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.HospedagemService;
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
@RequestMapping("hospedagem")
public class HospedagemController {

    @Autowired
    private HospedagemService service;

    @PreAuthorize("hasRole('MOD')")
    @GetMapping("index")
    public ResponseEntity index() {
        List<Hospedagem> hospedagens = new ArrayList<>();
        hospedagens = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(hospedagens);
    }

    @GetMapping(value = "index", params = {"id"})
    public ResponseEntity index(@RequestParam("id") Integer idHospedagem) {
        Hospedagem hospedagem = new Hospedagem();
        hospedagem = service.findByIdHospedagem(idHospedagem);

        if (hospedagem != null) {
            return ResponseEntity.status(HttpStatus.OK).body(hospedagem);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hospedagem não encontrada");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PreAuthorize("hasRole('MOD')")
    @PostMapping("create")
    public ResponseEntity create(@RequestBody Hospedagem data) {
        Hospedagem hospedagem = new Hospedagem();
        hospedagem = service.save(data);
        return ResponseEntity.status(HttpStatus.OK).body(hospedagem);
    }

    @PatchMapping("update")
    public ResponseEntity patch(@RequestParam("id") Integer idHospedagem, @RequestParam("idPedido") Long idPedido) {
        Hospedagem hospedagem = new Hospedagem();
        hospedagem = service.update(idHospedagem, idPedido);

        if (hospedagem != null && hospedagem.getDiaria() != -1) {
            return ResponseEntity.status(HttpStatus.OK).body(hospedagem);
        } else if (hospedagem == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hospedagem não encontrada");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Pedido não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
    }

    @PatchMapping(value = "update", params = {"func"})
    public ResponseEntity patch(@RequestBody Hospedagem data) {
        Hospedagem hospedagem = new Hospedagem();
        hospedagem = service.update(data);

        if (hospedagem != null) {
            return ResponseEntity.status(HttpStatus.OK).body(hospedagem);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hospedagem não encontrada");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    /*  TODO 
            Arrumar as rotas para que o usuário possa fazer update em serviços
            E o MOD possa atualizar os dados da hospedagem. Ex: data,preço,etc.
     */
    @PreAuthorize("hasRole('MOD')")
    @DeleteMapping("delete")
    @Transactional
    public ResponseEntity delete(@RequestParam("id") Integer idHospedagem) {
        int deleted = service.deleteByIdHospedagem(idHospedagem);
        Map<String, String> response = new HashMap<>();

        if (deleted >= 1) {
            response.put("message", "Hospedagem de id " + idHospedagem + "deletada com sucesso");
        } else {
            response.put("message", "Hospedagem de id " + idHospedagem + "não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
