/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Funcionario;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.FuncionarioService;
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
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("index")
    public ResponseEntity index() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
    }

    @GetMapping(value = "index", params = {"id"})
    public ResponseEntity index(@RequestParam("id") Integer id) {
        Funcionario funcionario = new Funcionario();
        funcionario = service.findByIdPessoa(id);

        if (funcionario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(funcionario);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Funcionário não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping(value = "index", params = {"cpf"})
    public ResponseEntity index(@RequestParam("cpf") String cpf) {
        Funcionario funcionario = new Funcionario();
        funcionario = service.findByCpf(cpf);

        if (funcionario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(funcionario);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Funcionário não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Funcionario data) {
        Funcionario funcionario = new Funcionario();
        funcionario = service.save(data);
        return ResponseEntity.status(HttpStatus.OK).body(funcionario);
    }

    @PatchMapping("update")
    public ResponseEntity patch(@RequestBody Funcionario data) {
        Funcionario funcionario = new Funcionario();
        funcionario = service.update(data);

        if (funcionario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(funcionario);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Funcionário não encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @DeleteMapping("delete")
    @Transactional
    public ResponseEntity delete(@RequestParam("cpf") String cpf) {
        int deleted = service.deleteByCpf(cpf);
        Map<String, String> response = new HashMap<>();

        if (deleted >= 1) {
            response.put("message", "Funcionário deletado com sucesso");
        } else {
            response.put("message", "Funcionário não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
