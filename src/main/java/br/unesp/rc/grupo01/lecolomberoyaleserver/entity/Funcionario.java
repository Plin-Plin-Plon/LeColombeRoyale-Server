/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author paulo
 */
@Entity
@Table(name = "Funcionario")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = {"cargo", "salario"})
@ToString(callSuper = true, includeFieldNames = true)
public class Funcionario extends Pessoa {

    @Column(nullable = false)
    private int cargo;

    @Column(nullable = false)
    private double salario;

    public Funcionario() {
    }
}
