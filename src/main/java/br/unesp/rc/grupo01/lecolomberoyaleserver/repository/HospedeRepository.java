/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.repository;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Hospede;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian
 */
@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
        
    @Override
    Hospede save(Hospede entity);
    
    String deleteByCpf(String cpf);
    
    Hospede findByIdPessoa(int idPessoa);
    
    Hospede findByCpf(String cpf);

    @Override
    List<Hospede> findAll();
}
