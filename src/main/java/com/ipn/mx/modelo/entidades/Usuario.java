/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author leoj_
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idUsuario;
    private String nombreUsuario;
    private String claveUsuario;
    private String nombre;
    private String paterno;
    private String materno;
    private String email;
    private String tipoUsuario;
    private String imagen;
    
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    
    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }
}
