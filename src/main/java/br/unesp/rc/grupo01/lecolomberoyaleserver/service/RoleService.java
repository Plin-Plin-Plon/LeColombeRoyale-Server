/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unesp.rc.grupo01.lecolomberoyaleserver.service;

import br.unesp.rc.grupo01.lecolomberoyaleserver.entity.Role;
import br.unesp.rc.grupo01.lecolomberoyaleserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author paulo
 */

@Component
public class RoleService {
    
    @Autowired
    private RoleRepository repository;
    
    public  RoleService(){
    }
    
    public Role findByName(String name){
        Role insertedEntity = null;
        
        if(repository != null){
            insertedEntity = repository.findByName(name);
        }
       
        return insertedEntity;
    }
}
