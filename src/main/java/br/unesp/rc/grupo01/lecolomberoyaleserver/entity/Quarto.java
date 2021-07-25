/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author paulo
 */
@Entity(name = "Quarto")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Quarto implements Serializable {

    @Id
    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Boolean vago = true;

    public Quarto() {
    }
}
