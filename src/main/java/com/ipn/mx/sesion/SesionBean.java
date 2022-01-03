/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.sesion;


import java.io.Serializable;

/**
 *
 * @author leoj_
 */
public class SesionBean implements Serializable{
    protected static final String ACC_ADMIN = "ADMIN";
    protected static final String ACC_USER = "USER";
    protected static final String ACC_UNLOGGED = "UNLOGGED";
    
    protected String accion;
    
    public boolean isModoAdmin(){
        if(accion != null){
            return accion.equals(ACC_ADMIN);
        }else{
            return false;
        }
    }
    
    public boolean isModoUser(){
        if(accion != null){
            return accion.equals(ACC_USER);
        }else{
            return false;
        }
    }
    
    public boolean isModoUnlogged(){
        if(accion != null){
            return accion.equals(ACC_UNLOGGED);
        }else{
            return false;
        }
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
