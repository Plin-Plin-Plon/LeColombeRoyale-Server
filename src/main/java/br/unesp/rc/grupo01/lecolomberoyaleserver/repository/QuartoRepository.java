/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.repository;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Quarto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian
 */
@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    
    @Override
    Quarto save(Quarto entity);
    
    int deleteByNumero(int numero);
    
    Quarto findByNumero(int numero);

    @Override
    List<Quarto> findAll();
}
