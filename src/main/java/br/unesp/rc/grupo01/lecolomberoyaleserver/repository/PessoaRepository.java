/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.repository;

import java.util.List;
import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author paulo
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
    @Override
    Pessoa save(Pessoa entity);
    
    Pessoa findByUsuario(String usuario);
    
    @Override
    List<Pessoa> findAll();
}
