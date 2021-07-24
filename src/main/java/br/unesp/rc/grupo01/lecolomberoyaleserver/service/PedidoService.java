/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Pedido;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.PedidoRepository;
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
public class PedidoService {
   
    @Autowired
    private PedidoRepository repository;

    public PedidoService() {
    }

    public Pedido save(Pedido entity) {
        Pedido persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.save(entity);
        }

        return persistedEntity;
    }

    public Pedido findByIdHospede(int idHospede) {
        Pedido insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByIdHospede(idHospede);
        }

        return insertedEntity;
    }

    public int deleteByIdHospede(int idHospede) {
        int deleted = -1;

        if (repository != null) {
            deleted = repository.deleteByIdHospede(idHospede);
        }

        return deleted;
    }

    public Pedido update(Pedido entity) {
        Pedido persistedEntity = null;
        
        if (repository != null) {
            int idHospede = entity.getIdHospede();
            persistedEntity = repository.findByIdHospede(idHospede);

            if (persistedEntity != null) {
                if (entity.getAvaliacaoItem() != null) {
                    persistedEntity.setAvaliacaoItem(entity.getAvaliacaoItem());
                }
                
                persistedEntity = repository.save(persistedEntity);
            }
        }

        return persistedEntity;
    }

    public List<Pedido> findAll() {
        List<Pedido> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findAll();
            Collections.sort(list, (a, b) -> a.getIdHospede() - b.getIdHospede());
        }

        return list;
    }
}
