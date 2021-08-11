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
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author paulo
 */
@Entity(name = "Hospedagem")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Hospedagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idHospedagem;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataChegada;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaida;

    @ManyToOne
    @JoinColumn(name = "idHospede", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Hospede hospede;

    @OneToOne
    @JoinColumn(name = "quarto_numero")
    private Quarto quarto;

    @Column(nullable = false)
    private double diaria = 0;

    @Column(nullable = false)
    private double valorTotal = 0;

    @OneToMany
    @JoinColumn(name = "idHospedagem")
    private List<Pedido> pedidos;

    public Hospedagem() {
        this.pedidos = new ArrayList<>();
    }

    public void setHospedagem(Pedido pedido) {
        this.pedidos.add(pedido);
    }
}
