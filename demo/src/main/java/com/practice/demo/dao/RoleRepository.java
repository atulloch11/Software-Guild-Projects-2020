/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.demo.dao;

import com.practice.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashleytulloch
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Role getRoleByRole(String string);
    
    Role getRoleById(Integer id);
}
