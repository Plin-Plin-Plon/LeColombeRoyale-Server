/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospedagem;
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Pedido;
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Quarto;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.HospedagemRepository;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.HospedeRepository;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.PedidoRepository;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.QuartoRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Christian
 */
@Service("hospedagemService")
public class HospedagemService {

    @Autowired
    private HospedagemRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    public HospedagemService() {
    }

    public Hospedagem save(Hospedagem entity) {
        Hospedagem persistedEntity = null;

        if (repository != null) {
            int hospede = entity.getHospede().getIdPessoa();
            int idQuarto = entity.getQuarto().getNumero();

            Quarto quarto = quartoRepository.findByNumero(idQuarto);

            entity.setHospede(hospedeRepository.findByIdPessoa(hospede));
            entity.setQuarto(quarto);
            entity.setDiaria(quarto.getValor());
            entity.setValorTotal(quarto.getValor());

            persistedEntity = repository.save(entity);
        }

        return persistedEntity;
    }

    public Hospedagem findByIdHospedagem(Long idHospedagem) {
        Hospedagem insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByIdHospedagem(idHospedagem);
        }

        return insertedEntity;
    }

    public Hospedagem findByHospedeIdPessoaAndAtual(Integer idPessoa, Boolean atual) {
        Hospedagem insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByHospedeIdPessoaAndAtual(idPessoa, atual);
        }

        return insertedEntity;
    }

    public int deleteByIdHospedagem(Long idHospedagem) {
        int deleted = -1;

        if (repository != null) {
            deleted = repository.deleteByIdHospedagem(idHospedagem);
        }

        return deleted;
    }

    public Hospedagem update(Hospedagem entity) {
        Hospedagem persistedEntity = null;

        if (repository != null) {
            Long idHospedagem = entity.getIdHospedagem();
            persistedEntity = repository.findByIdHospedagem(idHospedagem);

            if (persistedEntity != null) {
                if (entity.getAtual() != null && persistedEntity.getAtual() == true) {
                    persistedEntity.setAtual(entity.getAtual());
                }

                persistedEntity = repository.save(persistedEntity);
            }
        }

        return persistedEntity;
    }

    public Hospedagem update(Long idHospedagem, Long idPedido) {
        Hospedagem persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.findByIdHospedagem(idHospedagem);

            if (persistedEntity != null) {
                Pedido pedido = pedidoRepository.findByIdPedido(idPedido);

                if (pedido != null) {
                    double valorTotal = persistedEntity.getValorTotal();
                    double valorPedido = pedido.getServico().getPreco();

                    persistedEntity.setHospedagem(pedido);
                    persistedEntity.setValorTotal(valorTotal + valorPedido);

                    persistedEntity = repository.save(persistedEntity);
                } else {
                    persistedEntity.setDiaria(-1);
                }
            }
        }

        return persistedEntity;
    }

    public Hospedagem update(Long idHospedagem, Boolean atual) {
        Hospedagem persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.findByIdHospedagem(idHospedagem);

            if (persistedEntity != null) {
                if (atual != null && persistedEntity.getAtual() == true) {
                    persistedEntity.setAtual(atual);
                    persistedEntity = repository.save(persistedEntity);
                } else {
                    persistedEntity.setDiaria(-1);
                }
            }
        }

        return persistedEntity;
    }
    
    public List<Hospedagem> findAll() {
        List<Hospedagem> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findAll();
            Collections.sort(list, (a, b) -> {
                return Long.compare(a.getIdHospedagem(), b.getIdHospedagem());
            });
        }

        return list;
    }
}
