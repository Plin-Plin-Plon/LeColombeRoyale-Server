/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author paulo
 */
@Entity(name = "Pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPessoa;

    @Column(nullable = false)
    private String nome;

    @Length(min = 11, max = 14)
    @Column(name = "cpf", length = 14, unique = true, nullable = false)
    private String cpf;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String senha;

    @Column(nullable = false)
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "idPessoa")
    private List<Endereco> endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContato")
    private Contato contato;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles;

    public Pessoa() {
        this.endereco = new ArrayList<>();
    }

    public void setEndereco(Endereco endereco) {
        this.endereco.add(endereco);
    }
}
