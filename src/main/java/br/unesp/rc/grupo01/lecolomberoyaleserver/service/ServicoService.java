/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Servico;
import br.unesp.rc.grupo01.lecolomberoyaleserver.recommender.Recomendador;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.ServicoRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
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

    public List<Servico> recommendByIdPessoa(Integer idPessoa) {
        List<Servico> list = null;
        List<Servico> filteredList = null;

        if (repository != null) {
            list = new ArrayList<>();
            filteredList = new ArrayList<>();
            
            try {
                DataModel model = Recomendador.getCsvDataModel("data.csv");
 
                Recommender recommender = new Recomendador().buildRecommender(model);
                List<RecommendedItem> recommendations = recommender.recommend(idPessoa, 3);
                
                list = repository.findAll();

                System.out.println("O hóspede " + idPessoa + " pode gostar dos seguintes serviços baseado em nosso sistema:");
                for (RecommendedItem recommendation : recommendations) {
                    Servico servico = list.stream()
                            .filter(a -> Objects.equals(Long.valueOf(a.getIdServico()), recommendation.getItemID()))
                            .findFirst()
                            .orElse(null);
                    
                    filteredList.add(servico);
                    System.out.println("Serviço " + servico.getIdServico() + ": " + servico.getNome() + " - Avaliação aproximada: " + recommendation.getValue());
                }
            } catch (TasteException ex) {
                System.err.println("Erro no sistema de recomendação");
                ex.printStackTrace();
            } catch (IOException ex) {
                System.err.println("Erro no arquivo CSV");
                ex.printStackTrace();
            }
        }

        return filteredList;
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
