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

    public Pedido findByIdPedido(Long idPedido) {
        Pedido insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByIdPedido(idPedido);
        }

        return insertedEntity;
    }

    public List<Pedido> findByIdHospede(int idHospede) {
        List<Pedido> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findByIdHospede(idHospede);
            Collections.sort(list, (a, b) -> {
                return Long.compare(a.getIdPedido(), b.getIdPedido());
            });
        }

        return list;
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
            long idPedido = entity.getIdHospede();
            persistedEntity = repository.findByIdPedido(idPedido);

            if (persistedEntity != null) {
                if (entity.getAvaliacaoServico() != null) {
                    persistedEntity.setAvaliacaoServico(entity.getAvaliacaoServico());
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
