/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.repository;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Funcionario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
    @Override
    Funcionario save(Funcionario entity);
    
    String deleteByCpf(String cpf);
    
    Funcionario findByIdPessoa(int idPessoa);
    
    Funcionario findByCpf(String cpf);

    @Override
    List<Funcionario> findAll();
}
