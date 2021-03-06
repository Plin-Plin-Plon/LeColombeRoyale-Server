/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Pedido;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.PedidoService;
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
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PreAuthorize("hasRole('MOD')")
    @GetMapping("index")
    public ResponseEntity index() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @GetMapping(value = "index", params = {"concluido"})
    public ResponseEntity index(@RequestParam("concluido") Boolean concluido) {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos = service.findByConcluido(concluido);
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @GetMapping(value = "index", params = {"idPedido"})
    public ResponseEntity index(@RequestParam("idPedido") Long idPedido) {
        Pedido pedido = new Pedido();
        pedido = service.findByIdPedido(idPedido);
        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }

    @GetMapping(value = "index", params = {"idHospede"})
    public ResponseEntity index(@RequestParam("idHospede") Integer idHospede) {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos = service.findByHospedeIdPessoa(idHospede);
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Pedido data) {
        Pedido pedido = new Pedido();
        pedido = service.save(data);
        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }

    @PatchMapping("update")
    public ResponseEntity patch(@RequestBody Pedido data) {
        Pedido pedido = new Pedido();
        pedido = service.update(data);

        if (pedido != null) {
            return ResponseEntity.status(HttpStatus.OK).body(pedido);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Pedido n??o encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PreAuthorize("hasRole('MOD')")
    @DeleteMapping(value = "delete", params = {"idPedido"})
    @Transactional
    public ResponseEntity delete(@RequestParam("idPedido") Long idPedido) {
        int deleted = service.deleteByIdPedido(idPedido);
        Map<String, String> response = new HashMap<>();

        if (deleted >= 1) {
            response.put("message", "Pedido de id " + idPedido + " deletado com sucesso");
        } else {
            response.put("message", "Pedido de id " + idPedido + " n??o encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('MOD')")
    @DeleteMapping(value = "delete", params = {"idHospede"})
    @Transactional
    public ResponseEntity delete(@RequestParam("idHospede") Integer idHospede) {
        int deleted = service.deleteByHospedeIdPessoa(idHospede);
        Map<String, String> response = new HashMap<>();

        if (deleted >= 1) {
            response.put("message", "Pedidos do h??spede de id " + idHospede + " foram deletados com sucesso");
        } else {
            response.put("message", "Pedidos do h??spede de id " + idHospede + " n??o foram encontrados");
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
