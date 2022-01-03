
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Producto;
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
public class ProductoDAO {
    public void create(Producto dto){
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
    
    public void update(Producto dto){
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
    
    public Producto delete(Producto dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            
            //recuperar entidad completa
            dto=(s.get(dto.getClass(), dto.getIdProducto()));
            
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
    
    public Producto read(Producto dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            dto=(s.get(dto.getClass(), dto.getIdProducto()));
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
            Query q = s.createQuery("from Producto as c order by c.idProducto");
            lista = q.list();            
            
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
        return lista;
    }
    
    public static void main(String[] args){
        
        ProductoDAO dao = new ProductoDAO();
        Producto dto = new Producto();
       
        dto.setIdProducto(3);

        dto = dao.read(dto);
        dao.delete(dto);
        
        System.out.println(dao.readAll());
    }
}
