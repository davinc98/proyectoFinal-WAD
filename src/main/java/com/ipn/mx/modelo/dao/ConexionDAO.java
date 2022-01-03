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
        String user = "kkabazptrxlnez";
        String pwd = "f3963ccfcf4d86ccda2b5eab8547b43b33f601deab38753711e4d6f323273cad";
        String url="jdbc:postgresql://ec2-54-174-43-13.compute-1.amazonaws.com:5432/d9da4c8pst3ldd";
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
