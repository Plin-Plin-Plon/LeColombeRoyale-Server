/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Servico;
import java.io.Serializable;
import javax.persistence.CascadeType;
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

    @Column(nullable = false)
    private int idHospede;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "servico_idServico")
    private Servico servico;

    @Column(nullable = false)
    private Double avaliacaoServico = -1.0;

    public Pedido() {
    }
}
