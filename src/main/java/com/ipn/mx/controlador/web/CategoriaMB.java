package com.ipn.mx.controlador.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.entidades.Categoria;
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
@ManagedBean(name = "categoriaMB")
@SessionScoped
public class CategoriaMB extends BaseBean implements Serializable {
    

    private final CategoriaDAO dao = new CategoriaDAO();
    
    private Categoria dto;
    private List<Categoria> listaCategorias;

    public CategoriaMB() {}
    
    @PostConstruct
    public void init(){
        listaCategorias = new ArrayList<>();
        listaCategorias = dao.readAll();
    }

    public Categoria getDto() {
        return dto;
    }

    public void setDto(Categoria dto) {
        this.dto = dto;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
    
    
    public String preparedAdd(){
        dto = new Categoria();
        setAccion(ACC_CREAR);
        return "categoriaForm.xhtml?faces-redirect=true";
    }
    
    public String preparedGrafica(){
        return "graficaCategorias?faces-redirect=true";
    }
    
    public String preparedUpdate(){
        setAccion(ACC_ACTUALIZAR);
        return "categoriaForm.xhtml?faces-redirect=true";
    }
    
    public String preparedVisualise(){
        setAccion(ACC_VISUALIZAR);
        return "datosCategoria.xhtml?faces-redirect=true";
    }
    
    public String preparedListadoCategorias(){
        init();
        return "listadoCategorias.xhtml?faces-redirect=true";
    }
    
    public String preparedIndex(){
        return "index?faces-redirect=true";
    }
    
    public Boolean validate(){
        boolean valido = false;
        if(dto.getNombreCategoria()==null){
            //Realizar las validaciones
        }
        valido = true;
        return valido;
    }
    
    public String add(){
        Boolean valido = validate();
        if(valido){
            dao.create(dto);
            if(valido)
                return preparedListadoCategorias();
            else
                return preparedAdd();
        }
        return preparedAdd();
    }
    
    public String update(){
        Boolean valido = validate();
        if(valido){
            
            dao.update(dto);
            if(valido){
                return preparedListadoCategorias();
            }else{
                return preparedUpdate();
            }
        }
        return preparedUpdate();
    }
    
    public String delete(){
        dao.delete(dto);
        return preparedListadoCategorias();
    }
    
    public void seleccionarCategoria(ActionEvent event){
        String claveSel = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("claveSel");
        dto = new Categoria();
        dto.setIdCategoria(Long.parseLong(claveSel));
        try{
            dto = dao.read(dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
