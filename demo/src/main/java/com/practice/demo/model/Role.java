/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.demo.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author ashleytulloch
 */
@Entity
@Data
public class Role {
    
    @Id
    private int id;
    
    private String role;
}
