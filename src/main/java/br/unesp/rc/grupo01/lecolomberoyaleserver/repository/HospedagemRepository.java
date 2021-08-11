/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.repository;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospedagem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian
 */
@Repository
public interface HospedagemRepository extends JpaRepository<Hospedagem, Long> {
    
    @Override
    Hospedagem save(Hospedagem entity);
    
    int deleteByIdHospedagem(Long idHospedagem);
    
    Hospedagem findByIdHospedagem(Long idHospedagem);
    
    Hospedagem findByHospedeIdPessoa(Integer idPessoa);

    @Override
    List<Hospedagem> findAll();
}
