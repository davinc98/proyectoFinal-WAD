/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Categoria;
import com.ipn.mx.utilerias.HibernateUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author leoj_
 */
public class CategoriaDAO {
       
    public void create(Categoria dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            s.save(dto);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
    }
    
    public void update(Categoria dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            s.update(dto);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
    }
        
    public Categoria delete(Categoria dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
                
        try{
            tx.begin();
            
            //Recuperar la entidad completa
            dto = (s.get(dto.getClass(), dto.getIdCategoria()));
            
            s.delete(dto);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
            return null;
        }
        return dto;
    }
    
    public Categoria read(Categoria dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            dto = (s.get(dto.getClass(), dto.getIdCategoria()));
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
        return dto;
    }
    
    public List readAll(){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        List lista = new ArrayList();
        try{
            tx.begin();
            Query q = s.createQuery("from Categoria as c order by c.idCategoria");
            lista = q.list();           
//            for(Categoria c: (List<Categoria>)q.list()){
//                Categoria dto = new Categoria();
//                dto = c;
//                lista.add(dto);
//            }            
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
        return lista;
    }
    
    public static void main(String[] args){
        
        CategoriaDAO dao = new CategoriaDAO();
        Categoria dto = new Categoria();
       
//        dto.setIdCategoria(3);
        dto.setNombreCategoria("Categoria prueba X");
        dto.setDescripcionCategoria("Descripcion prueba x");
        dao.create(dto);
        
//        dao.delete(dto);
        
        System.out.println(dao.readAll());
    }
}
