/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author paulo
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Quarto {

    private int idQuarto;
    private int numero;
    private String tipo;
    private boolean vago;

    public Quarto() {
    }
}
