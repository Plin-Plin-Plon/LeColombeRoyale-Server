/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.PessoaRepository;
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Pessoa;

//import java.util.ArrayList;
//import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author paulo
 */
@Service("userService")
public class PessoaService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pessoa user = pessoaRepository.findByUsuario(username);
        if (user == null) {
            throw new UsernameNotFoundException("Nome de usuário ou senha inválidos");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getSenha(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Pessoa user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public Pessoa findByUsuario(String username) {
        return pessoaRepository.findByUsuario(username);
    }
    
    /*public List<Pessoa> findAll() {
        List<Pessoa> list = new ArrayList<>();
        pessoaRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }*/

}
