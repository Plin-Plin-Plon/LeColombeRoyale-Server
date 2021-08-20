/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Funcionario;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.FuncionarioRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Christian
 */
@Service("funcionarioService")
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public FuncionarioService() {
    }

    public Funcionario save(Funcionario entity) {
        Funcionario persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.save(entity);
        }

        return persistedEntity;
    }

    public Funcionario findByIdPessoa(int idPessoa) {
        Funcionario insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByIdPessoa(idPessoa);
        }

        return insertedEntity;
    }

    public Funcionario findByCpf(String cpf) {
        Funcionario insertedEntity = null;

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
    
        public Funcionario findByUsuario(String usuario) {
        Funcionario insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByUsuario(usuario);
        }

        return insertedEntity;
    }

    public Funcionario update(Funcionario entity) {
        Funcionario persistedEntity = null;

        if (repository != null) {
            int idPessoa = entity.getIdPessoa();
            persistedEntity = repository.findByIdPessoa(idPessoa);

            if (persistedEntity != null) {
                persistedEntity = repository.save(persistedEntity);
            }
        }

        return persistedEntity;
    }

    public List<Funcionario> findAll() {
        List<Funcionario> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findAll();
            Collections.sort(list, (a, b) -> a.getIdPessoa() - b.getIdPessoa());
        }

        return list;
    }
}
