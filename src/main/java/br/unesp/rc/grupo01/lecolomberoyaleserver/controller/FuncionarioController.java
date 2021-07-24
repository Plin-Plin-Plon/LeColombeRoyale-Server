/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Funcionario;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.FuncionarioService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Funcionario> index() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios = service.findAll();
        return funcionarios;
    }

    @GetMapping(value = "index", params = {"id"})
    public Funcionario index(@RequestParam("id") Integer id) {
        if (id != null) {
            Funcionario funcionario = new Funcionario();
            funcionario = service.findByIdPessoa(id);
            return funcionario;
        } else {
            return null;
        }
    }
    
    @GetMapping(value = "index", params = {"cpf"})
    public Funcionario index(@RequestParam("cpf") String cpf) {
        if (cpf != null) {
            Funcionario funcionario = new Funcionario();
            funcionario = service.findByCpf(cpf);
            return funcionario;
        } else {
            return null;
        }
    }

    @PostMapping("create")
    public Funcionario create(@RequestBody Funcionario data) {
        Funcionario funcionario = new Funcionario();
        funcionario = service.save(data);
        return funcionario;
    }

    /*
    @PatchMapping("update")
    public Funcionario patch(@RequestBody Funcionario data) {
        Funcionario Funcionario = new Funcionario();
        Funcionario = service.update(data);
        return Funcionario;
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