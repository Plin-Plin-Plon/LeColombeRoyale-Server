/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Quarto;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.QuartoRepository;
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
public class QuartoService {

    @Autowired
    private QuartoRepository repository;

    public QuartoService() {
    }

    public Quarto save(Quarto entity) {
        Quarto persistedEntity = null;

        if (repository != null) {
            int numero = entity.getNumero();
            persistedEntity = repository.findByNumero(numero);

            if (persistedEntity == null) {
                persistedEntity = repository.save(entity);
            } else {
                persistedEntity.setNumero(-1);
            }
        }

        return persistedEntity;
    }

    public Quarto findByNumero(int numero) {
        Quarto insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByNumero(numero);
        }

        return insertedEntity;
    }

    public int deleteByNumero(int numero) {
        int deleted = -1;

        if (repository != null) {
            deleted = repository.deleteByNumero(numero);
        }

        return deleted;
    }

    public Quarto update(Quarto entity) {
        Quarto persistedEntity = null;

        if (repository != null) {
            int numero = entity.getNumero();
            persistedEntity = repository.findByNumero(numero);

            if (persistedEntity != null) {
                if (entity.getTipo() != null) {
                    persistedEntity.setTipo(entity.getTipo());
                }

                if (entity.getVago() != null) {
                    persistedEntity.setVago(entity.getVago());
                }

                persistedEntity = repository.save(persistedEntity);
            }
        }

        return persistedEntity;
    }

    public List<Quarto> findAll() {
        List<Quarto> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findAll();
            Collections.sort(list, (a, b) -> a.getNumero() - b.getNumero());
        }

        return list;
    }
}
