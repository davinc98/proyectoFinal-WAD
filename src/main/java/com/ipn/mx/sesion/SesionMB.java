/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.sesion;

import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author leoj_
 */
@ManagedBean(name = "sesionMB")
@SessionScoped
public class SesionMB extends SesionBean implements Serializable {

    private String nombreUsuario = "";
    private String password = "";
    private final Sesion sesion = new Sesion();

    private static final Logger logger = Logger.getLogger(SesionMB.class.getName());

    public SesionMB() {
        logger.log(Level.INFO, "Creando el SesionSB .. !!!!!: ");
        setAccion(ACC_UNLOGGED);
    }

    public String preparedLogin() {
        if (sesion.getAttrRequest("tipo") == null) {
            setAccion(ACC_UNLOGGED);
            return "login.xhtml?faces-redirect=true";
        }
        //REDIRECCIONAR AL INICIO
        return "index.xhtml?faces-redirect=true";
    }


    public String validarCredenciales() {
        logger.log(Level.INFO, "validando datos...: ");

        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios = dao.readAll();

        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario user = listaUsuarios.get(i);
            if (nombreUsuario.equals(user.getNombreUsuario())
                    && password.equals(user.getClaveUsuario())) {
                sesion.setAttrRequest("tipo", user.getTipoUsuario());

                setAccion(ACC_UNLOGGED);
                
                if (user.getTipoUsuario().equals("Administrador")) {
                    setAccion(ACC_ADMIN);
                } else if (user.getTipoUsuario().equals("Usuario")) {
                    setAccion(ACC_USER);
                }
                return "index.xhtml?faces-redirect=true";
            } else {
                //Credenciales incorrectas
            }
        }
        //REDIRECCIONAR AL INICIO
        return "errorLogin.xhtml?faces-redirect=true";
    }

    public String cerrarSesion() {
        logger.log(Level.INFO, "Entrando a cerrar sesion: ");

//        sesion.getSession().invalidate();
        setAccion(ACC_UNLOGGED);
        return "index?faces-redirect=true";
    }

    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Cerrando Sesion!!");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
