/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospedagem;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.HospedagemRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Christian
 */
@Component
public class HospedagemService {
    
    @Autowired
    private HospedagemRepository repository;

    public HospedagemService() {
    }

    public Hospedagem save(Hospedagem entity) {
        Hospedagem persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.save(entity);
        }

        return persistedEntity;
    }

    public Hospedagem findByIdHospedagem(int idHospedagem) {
        Hospedagem insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByIdHospedagem(idHospedagem);
        }

        return insertedEntity;
    }

    public int deleteByIdHospedagem(int idHospedagem) {
        int deleted = -1;

        if (repository != null) {
            deleted = repository.deleteByIdHospedagem(idHospedagem);
        }

        return deleted;
    }

    
    public Hospedagem update(Hospedagem entity) {
        Hospedagem persistedEntity = null;
        
        if (repository != null) {
            int idHospedagem = entity.getIdHospedagem();
            persistedEntity = repository.findByIdHospedagem(idHospedagem);

            if (persistedEntity != null) {
                /*
                if (entity.() != null) {
                    persistedEntity.setNome(entity.getNome());
                }

                if (entity.getDescricao() != null) {
                    persistedEntity.setDescricao(entity.getDescricao());
                }
                
                if (entity.getPreco() != null) {
                    persistedEntity.setPreco(entity.getPreco());
                }
                */
                persistedEntity = repository.save(persistedEntity);
            }
        }

        return persistedEntity;
    }

    public List<Hospedagem> findAll() {
        List<Hospedagem> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findAll();
            Collections.sort(list, (a, b) -> a.getIdHospedagem() - b.getIdHospedagem());
        }

        return list;
    }
}
