/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author paulo
 */
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Acesso implements Serializable {

    @Column(name = "acesso_usuario")
    private String usuario;

    @Column(name = "acesso_senha")
    private String senha;

    @Column(name = "acesso_token")
    private String token;

    public Acesso() {
    }
}
