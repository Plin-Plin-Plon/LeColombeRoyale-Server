/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.controller;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Acesso;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author paulo
 */
@RestController
public class AcessoController {

    @PostMapping("acesso")
    public Acesso login(@RequestBody Acesso data) {
        String token = getJWTToken(data.getUsuario());

        /* TODO:
            verificar usuario no banco (existe ou nao) e ver se senha bate
         */
        Acesso acesso = new Acesso();
        acesso.setUsuario(data.getUsuario());
        acesso.setSenha(data.getSenha());
        acesso.setToken(token);

        return acesso;
    }

    private String getJWTToken(String usuario) {
        String senha = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        Key key = new SecretKeySpec(senha.getBytes(),0,senha.getBytes().length, SignatureAlgorithm.HS512.getJcaName());
        
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
