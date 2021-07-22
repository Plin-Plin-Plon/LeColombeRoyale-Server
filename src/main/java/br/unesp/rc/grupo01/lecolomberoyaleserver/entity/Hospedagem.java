/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
public class Hospedagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idHospedagem;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataChegada;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaida;
    
    private float diaria;
    private float valorTotal;
    
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "hospedagem_idHospedagem")
    private List<Servico> servico;

    public Hospedagem() {
        this.servico = new ArrayList<>();
    }

    public void setHospedagem(Servico servico) {
        this.servico.add(servico);
    }
}
