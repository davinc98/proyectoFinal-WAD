/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Usuario;
import com.ipn.mx.utilerias.HibernateUtil;
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
public class UsuarioDAO {
    public void create(Usuario dto){
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
    
    public void update(Usuario dto){
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
    
    public Usuario delete(Usuario dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            
            //recuperar entidad completa
            dto=(s.get(dto.getClass(), dto.getIdUsuario()));
            
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
    
    public Usuario read(Usuario dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            dto = (s.get(dto.getClass(), dto.getIdUsuario()));
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
            Query q = s.createQuery("from Usuario as c order by c.idUsuario");

            for(Usuario c: (List<Usuario>)q.list()){
                Usuario dto = new Usuario();
                dto=(c);
                lista.add(dto);
            }
            
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
        return lista;
    }
    
    public static void main(String[] args){
        
        UsuarioDAO dao = new UsuarioDAO();
        Usuario dto = new Usuario();
       
//        dto.setIdUsuario(3);
//
//        dto = dao.read(dto);
//        dao.delete(dto);
        
        System.out.println(dao.readAll());
    }
}
