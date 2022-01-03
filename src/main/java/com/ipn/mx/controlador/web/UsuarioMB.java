/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.entidades.Usuario;
import com.ipn.mx.utilerias.EnviarMail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author leoj_
 */
@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB extends BaseBean implements Serializable {
    private final UsuarioDAO dao = new UsuarioDAO();
    
    private Usuario dto;
    private List<Usuario> listaUsuarios;
   
    /**
     * Creates a new instance of ProductoMB
     */
    public UsuarioMB() {
    }
    
    @PostConstruct
    public void init(){
        listaUsuarios = new ArrayList<>();        
        listaUsuarios = dao.readAll();
    }

    public Usuario getDto() {
        return dto;
    }

    public void setDto(Usuario dto) {
        this.dto = dto;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    
    
    
    
    
    public String preparedAdd(){
        dto = new Usuario();
        setAccion(ACC_CREAR);
        return "usuarioForm.xhtml?faces-redirect=true";
    }
    
    public String preparedLogin(){
        setAccion(ACC_CREAR);
        return "login?faces-redirect=true";
    }
    
    public String preparedUpdate(){
        setAccion(ACC_ACTUALIZAR);
        return "usuarioForm.xhtml?faces-redirect=true";
    }
    
    public String preparedVisualise(){
        setAccion(ACC_VISUALIZAR);
        return "datosUsuario.xhtml?faces-redirect=true";
    }
    
    public String preparedListadoUsuarios(){
        init();
        return "listadoUsuarios?faces-redirect=true";
    }
    
    
    public Boolean validate(){
        boolean valido = true;
        if(dto.getNombre()==null){
            //Realizar las validaciones
        }
        
        return valido;
    }
    
    public String add(){
        Boolean valido = validate();
        if(valido){
           
            dao.create(dto);
            if(valido)
                return preparedListadoUsuarios();
            else
                return preparedAdd();
        }
        return preparedAdd();
    }
    
    public String register(){
        Boolean valido = validate();
        EnviarMail mail = new EnviarMail();
        if(valido){
           
            dao.create(dto);
            if(valido){
                mail.enviarCorreo(dto.getEmail(), "Usuario Registrado", 
                        "Usted ha sido registrado satisfactoriamente!\n Bienvenido "+dto.getNombre()+"!\n\n Usuario: "+dto.getNombreUsuario()+"\n Clave: "+dto.getClaveUsuario()+" ");
                return preparedLogin();
            }else{
                return preparedAdd();
            }
        }
        return preparedAdd();
    }
    
    public String update(){
        Boolean valido = validate();
        if(valido){
            
            dao.update(dto);
            if(valido){
                return preparedListadoUsuarios();
            }else{
                return preparedUpdate();
            }
        }
        return preparedUpdate();
    }
    
    public String delete(){
        dao.delete(dto);
        return preparedListadoUsuarios();
    }
    
    public void seleccionarUsuario(ActionEvent event){
        String claveSel = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("claveSel");
        dto = new Usuario();
        dto.setIdUsuario(Integer.parseInt(claveSel));
        try{
            dto = dao.read(dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
