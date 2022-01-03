/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.entidades.Categoria;
import com.ipn.mx.modelo.entidades.Producto;
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
@ManagedBean(name = "productoMB")
@SessionScoped
public class ProductoMB extends BaseBean implements Serializable {
    private final ProductoDAO dao = new ProductoDAO();
    private final CategoriaDAO daoCat = new CategoriaDAO();
    
    private Producto dto;
    private List<Producto> listaProducto;
    private List<Categoria> listaCategoria;
    
    private boolean msg = false;
    private String mensaje = "";
    private String alert = "";

    /**
     * Creates a new instance of ProductoMB
     */
    public ProductoMB() {
        listaCategoria = daoCat.readAll();
    }
    
    @PostConstruct
    public void init(){
        listaCategoria = new ArrayList<>();
        listaProducto = new ArrayList<>();
        
        listaCategoria = daoCat.readAll();
        listaProducto = dao.readAll();
        msg=false;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public Producto getDto() {
        return dto;
    }

    public void setDto(Producto dto) {
        this.dto = dto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }
    
    
    
    
    public String preparedAdd(){
        dto = new Producto();
        setAccion(ACC_CREAR);
        return "productoForm.xhtml?faces-redirect=true";
    }
    
    public String preparedUpdate(){
        setAccion(ACC_ACTUALIZAR);
        return "productoForm.xhtml?faces-redirect=true";
    }
    
    public String preparedVisualise(){
        setAccion(ACC_VISUALIZAR);
        return "datosProducto.xhtml?faces-redirect=true";
    }
    
    public String preparedListadoProductos(){
        init();
        return "listadoProductos?faces-redirect=true";
    }
    
    public String preparedListadoCategorias(){
        return "listadoCategorias?faces-redirect=true";
    }
    
    public String preparedIndex(){
        return "index?faces-redirect=true";
    }
    
    public String preparedGrafica(){
        return "graficaProductos?faces-redirect=true";
    }
    
    public Boolean validate(){
        boolean valido = true;
        if(dto.getNombreProducto()==null){
            //Realizar las validaciones
        }
        
        return valido;
    }
    
    public String add(){
        Boolean valido = validate();
        if(valido){
            Categoria cat = new Categoria();
            cat.setIdCategoria(dto.getIdCat());
            cat = daoCat.read(cat);
            dto.setIdCategoria(cat);
            
            dao.create(dto);
            if(valido)
                return preparedListadoProductos();
            else
                return preparedAdd();
        }
        return preparedAdd();
    }
    
    public String update(){
        Boolean valido = validate();
        if(valido){
            Categoria cat = new Categoria();
            cat.setIdCategoria(dto.getIdCat());
            cat = daoCat.read(cat);
            dto.setIdCategoria(cat);
            
            dao.update(dto);
            if(valido){
                msg=true;
            mensaje = "Producto ["+dto.getIdProducto()+"] Actualizado";
            alert = "alert-success";
                return preparedListadoProductos();
            }else{
                return preparedUpdate();
            }
        }
        return preparedUpdate();
    }
    
    public String delete(){
        dao.delete(dto);
        return preparedListadoProductos();
    }
    
    public void seleccionarProducto(ActionEvent event){
        String claveSel = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("claveSel");
        dto = new Producto();
        dto.setIdProducto(Integer.parseInt(claveSel));
        try{
            dto = dao.read(dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void resetarMensaje(){
        msg=false;
        mensaje="";
        alert="";
    }
}
