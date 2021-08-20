/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.repository;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author paulo
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
        
    Role findByName(String name);
}
