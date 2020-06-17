/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.demo.controller;

import com.practice.demo.dao.RoleRepository;
import com.practice.demo.dao.UserRepository;
import com.practice.demo.model.Role;
import com.practice.demo.model.User;
import com.practice.demo.service.ValidateService;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ashleytulloch
 */
@Controller
public class LoginController {
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    RoleRepository roleRepo;
    
    @Autowired
    ValidateService service;
    
    @Autowired
    PasswordEncoder encoder;
    
    private Set<String> violations = new HashSet();
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @GetMapping("addUser")
    public String displayCreateUserForm(Model model) {
        if (!violations.isEmpty()) {
            model.addAttribute("violations", violations);
        }
        return "addUser";
    }
    
    
    @PostMapping("createUser")
    public String createUser(String username, String password, HttpServletRequest request) {
        violations.clear();
        User user = new User();
        user.setUsername(username);
        user.setEnabled(true);
   
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleRepo.getRoleByRole("ROLE_USER"));
        user.setRoles(userRoles);
        
        
        service.validatePassword(violations, password, request.getParameter("confirmPassword"));
        if (!violations.isEmpty()) {
            return "redirect:/addUser";
        }
        
        user.setPassword(encoder.encode(password));
        userRepo.save(user);
        return "redirect:/content";
    }
}
