/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.demo.dao;

import com.practice.demo.model.Beer;
import com.practice.demo.model.User;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashleytulloch
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    User getUserByUsername(String username);
    
    User getUserById(Integer id);
    
}
