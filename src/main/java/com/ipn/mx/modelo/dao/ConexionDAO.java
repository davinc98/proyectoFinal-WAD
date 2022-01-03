/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author leoj_
 */
public class ConexionDAO {
    
    private Connection conexion;
    public Connection conectar(){
        String user = "xeuypkajvsxnme";
        String pwd = "8a7dbc29094673aa7ffe757ccf3b91b8efa90dcae15a2df8d3517d226ada74ba";
        String url="jdbc:postgresql://ec2-174-129-37-144.compute-1.amazonaws.com:5432/d52fhuga0cbijr";
        String pgDriver = "org.postgresql.Driver";
        
        try{
            Class.forName(pgDriver);
            conexion = DriverManager.getConnection(url, user, pwd);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conexion;
    }
    
}
