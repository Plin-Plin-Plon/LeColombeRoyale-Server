/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Servico;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.ServicoRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Christian
 */
@Service("servicoService")
public class ServicoService {
    
    @Autowired
    private ServicoRepository repository;

    public ServicoService() {
    }

    public Servico save(Servico entity) {
        Servico persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.save(entity);
        }

        return persistedEntity;
    }

    public Servico findByIdServico(int idServico) {
        Servico insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByIdServico(idServico);
        }

        return insertedEntity;
    }

    public int deleteByIdServico(int idServico) {
        int deleted = -1;

        if (repository != null) {
            deleted = repository.deleteByIdServico(idServico);
        }

        return deleted;
    }

    public Servico update(Servico entity) {
        Servico persistedEntity = null;
        
        if (repository != null) {
            int idServico = entity.getIdServico();
            persistedEntity = repository.findByIdServico(idServico);

            if (persistedEntity != null) {
                if (entity.getNome() != null) {
                    persistedEntity.setNome(entity.getNome());
                }

                if (entity.getDescricao() != null) {
                    persistedEntity.setDescricao(entity.getDescricao());
                }
                
                if (entity.getPreco() != null) {
                    persistedEntity.setPreco(entity.getPreco());
                }
                
                persistedEntity = repository.save(persistedEntity);
            }
        }

        return persistedEntity;
    }

    public List<Servico> findAll() {
        List<Servico> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findAll();
            Collections.sort(list, (a, b) -> a.getIdServico() - b.getIdServico());
        }

        return list;
    }
}
