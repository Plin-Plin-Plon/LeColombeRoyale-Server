/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.repository;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    @Override
    Pedido save(Pedido entity);
    
    int deleteByIdPedido(Long idPedido);
    
    int deleteByHospedeIdPessoa(int idPessoa);
    
    Pedido findByIdPedido(Long idPedido);
    
    List<Pedido> findByHospedeIdPessoa(int idPessoa);

    @Override
    List<Pedido> findAll();
}
