/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Acesso;
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Contato;
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Endereco;
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Funcionario;
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospede;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.FuncionarioService;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.HospedeService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author paulo
 */
@RestController
@RequestMapping("acesso")
public class AcessoController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private HospedeService hospedeService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody Acesso data) {
        String token = getJWTToken(data.getUsuario());

        Acesso acesso = new Acesso();
        acesso.setUsuario(data.getUsuario());
        acesso.setSenha(data.getSenha());
        acesso.setToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(acesso);
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody Hospede data) {
        Hospede hospede = new Hospede();
        List<Endereco> enderecos = new ArrayList<>();
        Acesso acesso = new Acesso();
        Contato contato = new Contato();

        enderecos = data.getEndereco();
        acesso = data.getAcesso();
        contato = data.getContato();

        hospede.setNome(data.getNome());
        hospede.setCpf(data.getCpf());
        hospede.setPremium(data.getPremium());

        for (Endereco endereco : enderecos) {
            hospede.setEndereco(endereco);
        }

        hospede.setAcesso(acesso);
        hospede.setContato(contato);

        hospede = hospedeService.save(hospede);

        /* TODO
            Linkar com a role correspondente
         */
        return ResponseEntity.status(HttpStatus.OK).body(hospede);
    }

    @PostMapping(value = "register", params = {"func"})
    public ResponseEntity register(@RequestBody Funcionario data) {
        Funcionario funcionario = new Funcionario();
        List<Endereco> enderecos = new ArrayList<>();
        Acesso acesso = new Acesso();
        Contato contato = new Contato();

        enderecos = data.getEndereco();
        acesso = data.getAcesso();
        contato = data.getContato();

        funcionario.setNome(data.getNome());
        funcionario.setCpf(data.getCpf());
        funcionario.setCargo(data.getCargo());
        funcionario.setSalario(data.getSalario());

        for (Endereco endereco : enderecos) {
            funcionario.setEndereco(endereco);
        }

        funcionario.setAcesso(acesso);
        funcionario.setContato(contato);
        
        funcionario = funcionarioService.save(funcionario);

        /* TODO
            Linkar com a role correspondente
         */
        return ResponseEntity.status(HttpStatus.OK).body(funcionario);
    }

    private String getJWTToken(String usuario) {
        String senha = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        Key key = new SecretKeySpec(senha.getBytes(), 0, senha.getBytes().length, SignatureAlgorithm.HS512.getJcaName());

        String token = Jwts
                .builder()
                .setSubject(usuario)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return "Bearer " + token;
    }
}
