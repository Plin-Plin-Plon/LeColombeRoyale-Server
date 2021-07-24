/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Christian
 */
@Entity(name = "Pedido")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Pedido implements Serializable {
    
    @Id
    private int idHospede;

    private int idItem;
    private Double avaliacaoItem;
    
    public Pedido() {
    }
}
