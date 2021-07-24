/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Pedido;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.PedidoService;
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
@RequestMapping("pedido")
public class PedidoController {
    
    @Autowired
    private PedidoService service;

    @GetMapping("index")
    public List<Pedido> index() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos = service.findAll();
        return pedidos;
    }

    @GetMapping(value = "index", params = {"id"})
    public Pedido index(@RequestParam("id") Integer idHospede) {
        if (idHospede != null) {
            Pedido pedido = new Pedido();
            pedido = service.findByIdHospede(idHospede);
            return pedido;
        } else {
            return null;
        }
    }

    @PostMapping("create")
    public Pedido create(@RequestBody Pedido data) {
        Pedido pedido = new Pedido();
        pedido = service.save(data);
        return pedido;
    }

    @PatchMapping("update")
    public Pedido patch(@RequestBody Pedido data) {
        Pedido pedido = new Pedido();
        pedido = service.update(data);
        return pedido;
    }

    @DeleteMapping("delete")
    @Transactional
    public int delete(@RequestParam("id") Integer idHospede) {
        int deleted = -1;

        if (idHospede != null) {
            deleted = service.deleteByIdHospede(idHospede);
        }

        return deleted;
    }
}
