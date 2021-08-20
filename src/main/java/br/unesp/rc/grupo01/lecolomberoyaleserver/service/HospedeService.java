/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospede;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.HospedeRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Christian
 */
@Service("hospedeService")
public class HospedeService {

    @Autowired
    private HospedeRepository repository;

    public HospedeService() {
    }

    public Hospede save(Hospede entity) {
        Hospede persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.save(entity);
        }

        return persistedEntity;
    }

    public Hospede findByIdPessoa(int idPessoa) {
        Hospede insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByIdPessoa(idPessoa);
        }

        return insertedEntity;
    }

    public Hospede findByUsuario(String usuario) {
        Hospede insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByUsuario(usuario);
        }

        return insertedEntity;
    }

    public Hospede findByCpf(String cpf) {
        Hospede insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByCpf(cpf);
        }

        return insertedEntity;
    }

    public int deleteByCpf(String cpf) {
        int deleted = -1;

        if (repository != null) {
            deleted = repository.deleteByCpf(cpf);
        }

        return deleted;
    }

    public Hospede update(Hospede entity) {
        Hospede persistedEntity = null;

        if (repository != null) {
            int idPessoa = entity.getIdPessoa();
            persistedEntity = repository.findByIdPessoa(idPessoa);

            if (persistedEntity != null) {
                persistedEntity = repository.save(persistedEntity);
            }
        }

        return persistedEntity;
    }

    public List<Hospede> findAll() {
        List<Hospede> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findAll();
            Collections.sort(list, (a, b) -> a.getIdPessoa() - b.getIdPessoa());
        }

        return list;
    }
}
