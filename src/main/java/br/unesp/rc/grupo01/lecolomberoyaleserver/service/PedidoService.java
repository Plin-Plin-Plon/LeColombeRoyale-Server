/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospedagem;
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Pedido;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.HospedeRepository;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.PedidoRepository;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.QuartoRepository;
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
@Service("pedidoService")
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private HospedagemService hospedagemService;

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public PedidoService() {
    }

    public Pedido save(Pedido entity) {
        Pedido persistedEntity = null;

        if (repository != null) {
            int hospede = entity.getHospede().getIdPessoa();
            int quarto = entity.getQuarto().getNumero();
            int servico = entity.getServico().getIdServico();

            entity.setHospede(hospedeRepository.findByIdPessoa(hospede));
            entity.setQuarto(quartoRepository.findByNumero(quarto));
            entity.setServico(servicoRepository.findByIdServico(servico));

            persistedEntity = repository.save(entity);

            List<Hospedagem> hospedagens = entity.getHospede().getHospedagem();
            Long idHospedagemAtual = -1l;

            for (Hospedagem hospedagem : hospedagens) {
                if (hospedagem.getAtual() == true) {
                    idHospedagemAtual = hospedagem.getIdHospedagem();
                    break;
                }
            }

            hospedagemService.update(idHospedagemAtual, persistedEntity.getIdPedido());
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

    public List<Pedido> findByHospedeIdPessoa(int idPessoa) {
        List<Pedido> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findByHospedeIdPessoa(idPessoa);
            Collections.sort(list, (a, b) -> {
                return Long.compare(a.getIdPedido(), b.getIdPedido());
            });
        }

        return list;
    }

    public List<Pedido> findByConcluido(Boolean concluido) {
        List<Pedido> list = null;

        if (repository != null) {
            list = new ArrayList<>();
            list = repository.findByConcluido(concluido);
            Collections.sort(list, (a, b) -> {
                return Long.compare(a.getIdPedido(), b.getIdPedido());
            });
        }

        return list;
    }

    public int deleteByIdPedido(Long idPedido) {
        int deleted = -1;

        if (repository != null) {
            deleted = repository.deleteByIdPedido(idPedido);
        }

        return deleted;
    }

    public int deleteByHospedeIdPessoa(int idPessoa) {
        int deleted = -1;

        if (repository != null) {
            deleted = repository.deleteByHospedeIdPessoa(idPessoa);
        }

        return deleted;
    }

    public Pedido update(Pedido entity) {
        Pedido persistedEntity = null;

        if (repository != null) {
            long idPedido = entity.getIdPedido();
            persistedEntity = repository.findByIdPedido(idPedido);

            if (persistedEntity != null) {
                if (entity.getAvaliacaoServico() != null) {
                    persistedEntity.setAvaliacaoServico(entity.getAvaliacaoServico());
                }

                if (entity.getConcluido() != false) {
                    persistedEntity.setConcluido(entity.getConcluido());
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
            Collections.sort(list, (a, b) -> {
                return Long.compare(a.getIdPedido(), b.getIdPedido());
            });
        }

        return list;
    }
}
