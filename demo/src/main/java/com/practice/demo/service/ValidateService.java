/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.demo.service;

import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashleytulloch
 */
@Service
public class ValidateService {
    
    public void validatePassword(Set<String> violations, String password, String confirmPassword) {
        
        if (password.isBlank()) {
            violations.add("You must input a password.");
        }
        
        if (!password.equals(confirmPassword)) {
            violations.add("Passwords do not match.");
        }
        
        
    }
    
}
