/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import java.util.ArrayList;
import java.util.List;
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
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true, includeFieldNames=true)
public class Hospede extends Pessoa {
    
    private boolean premium;
    private List<Hospedagem> hospedagem;
    
    
    public Hospede() {
        this.hospedagem = new ArrayList<>();
    }
    
    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem.add(hospedagem);
    }
}
