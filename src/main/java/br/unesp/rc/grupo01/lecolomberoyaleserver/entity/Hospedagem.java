/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author paulo
 */
public class Hospedagem {

    private int idHospedagem;
    private Date dataChegada;
    private Date dataSaida;
    private float diaria;
    private float valorTotal;
    private List<Servico> servico;

    public Hospedagem() {
        this.servico = new ArrayList<>();
    }

    public void setHospedagem(Servico servico) {
        this.servico.add(servico);
    }
}
