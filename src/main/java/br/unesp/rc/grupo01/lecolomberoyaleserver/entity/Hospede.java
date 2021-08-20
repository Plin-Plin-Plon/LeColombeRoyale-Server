/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author paulo
 */
@Entity
@Table(name = "Hospede")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = {"premium", "hospedagem"})
@ToString(callSuper = true, includeFieldNames = true)
public class Hospede extends Pessoa {

    @Column(nullable = false)
    private Boolean premium;

    @OneToMany(
            mappedBy = "hospede",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Hospedagem> hospedagem;

    public Hospede() {
        this.hospedagem = new ArrayList<>();
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem.add(hospedagem);
    }
}
