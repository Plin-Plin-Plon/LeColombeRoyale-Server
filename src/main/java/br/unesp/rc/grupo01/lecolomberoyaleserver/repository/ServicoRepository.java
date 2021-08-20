/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.repository;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Servico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian
 */
@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    
    @Override
    Servico save(Servico entity);
    
    int deleteByIdServico(int idServico);
    
    Servico findByIdServico(int idServico);

    @Override
    List<Servico> findAll();
}
