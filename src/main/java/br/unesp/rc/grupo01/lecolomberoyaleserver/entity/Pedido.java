/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPedido;

    @OneToOne
    @JoinColumn(name = "idHospede")
    private Hospede hospede;

    @OneToOne
    @JoinColumn(name = "numero")
    private Quarto quarto;

    @OneToOne
    @JoinColumn(name = "idServico")
    private Servico servico;

    private Double avaliacaoServico;

    @Column(nullable = false)
    private Boolean concluido;

    public Pedido() {
    }
}
