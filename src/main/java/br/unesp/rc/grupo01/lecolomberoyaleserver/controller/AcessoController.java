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
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Role;
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.AuthToken;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.FuncionarioService;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.HospedeService;
import br.unesp.rc.grupo01.lecolomberoyaleserver.security.TokenProvider;
import br.unesp.rc.grupo01.lecolomberoyaleserver.service.RoleService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private HospedeService hospedeService;

    @PostMapping("login")
    public ResponseEntity<?> generateToken(@RequestBody Acesso acesso) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        acesso.getUsuario(),
                        acesso.getSenha()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody Hospede data) {
        Hospede hospede = new Hospede();
        List<Endereco> enderecos = new ArrayList<>();
        Contato contato = new Contato();

        enderecos = data.getEndereco();
        contato = data.getContato();

        hospede.setUsuario(data.getUsuario());
        hospede.setSenha(bcryptEncoder.encode(data.getSenha()));

        hospede.setNome(data.getNome());
        hospede.setCpf(data.getCpf());
        hospede.setPremium(data.getPremium());

        for (Endereco endereco : enderecos) {
            hospede.setEndereco(endereco);
        }

        hospede.setContato(contato);

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        hospede.setRoles(roleSet);

        hospede = hospedeService.save(hospede);

        return ResponseEntity.status(HttpStatus.OK).body(hospede);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "register", params = {"func"})
    public ResponseEntity register(@RequestBody Funcionario data) {
        Funcionario funcionario = new Funcionario();
        List<Endereco> enderecos = new ArrayList<>();
        Contato contato = new Contato();

        funcionario.setUsuario(data.getUsuario());
        funcionario.setSenha(bcryptEncoder.encode(data.getSenha()));

        enderecos = data.getEndereco();
        contato = data.getContato();

        funcionario.setNome(data.getNome());
        funcionario.setCpf(data.getCpf());
        funcionario.setCargo(data.getCargo());
        funcionario.setSalario(data.getSalario());

        for (Endereco endereco : enderecos) {
            funcionario.setEndereco(endereco);
        }

        funcionario.setContato(contato);

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        role = roleService.findByName("MOD");
        roleSet.add(role);

        funcionario.setRoles(roleSet);

        funcionario = funcionarioService.save(funcionario);

        return ResponseEntity.status(HttpStatus.OK).body(funcionario);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("adminping")
    public String adminPing() {
        return "Apenas o Admnistrador pode ver esta rota";
    }

    @PreAuthorize("hasRole('MOD')")
    @GetMapping("modping")
    public String modPing() {
        return "Apenas o Admnistrador e os funcionários podem ver esta rota";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("userping")
    public String userPing() {
        return "Qualquer usuário tem acesso à esta rota";
    }

}
