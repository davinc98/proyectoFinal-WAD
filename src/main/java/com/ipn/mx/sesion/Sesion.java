/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.sesion;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leoj_
 */
public class Sesion {
    private static Sesion sesion;
    
    private FacesContext context;
    private HttpServletResponse httpServletResponse;
    private HttpServletRequest httpServletRequest;
    HttpSession session;
    
    private static final Logger logger = Logger.getLogger(Sesion.class.getName());
    
    public static Sesion getInstance(){
        if(sesion == null){
            sesion = new Sesion();
            logger.log(Level.INFO, "Creando clase Sesion .. !!!!!: ");
        }
        return sesion;
    }
    
    public Sesion(){
        context = FacesContext.getCurrentInstance();
        httpServletResponse = (HttpServletResponse)context.getExternalContext().getResponse();
        httpServletRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        session = (HttpSession) context.getExternalContext().getSession(false);
    }
    
    public HttpServletResponse getResponse() {
        context = FacesContext.getCurrentInstance();
        httpServletResponse = (HttpServletResponse)context.getExternalContext().getResponse();
        return httpServletResponse;
    }

    public HttpServletRequest getRequest() {
        context = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        return httpServletRequest;
    }
    
    public String getParamRequest(String nomParam) {
        HttpServletRequest request = getRequest();
        return request.getParameter(nomParam);
    }

    public Object getAttrRequest(String nomAttr) {
        HttpServletRequest request = getRequest();
        return request.getAttribute(nomAttr);
    }
    
    public void setAttrRequest(String attrName, Object attr) {
        HttpServletRequest request = getRequest();
        request.setAttribute(attrName, attr);
    }
    

    public HttpSession getSession() {
        return session;
    }

    public Object getAttrSession(String attrName) {
        session = getSession();
        return session.getAttribute(attrName);
    }
    
    public void setAttrSession(String attrName, Object attr) {
        session = getSession();
        session.setAttribute(attrName, attr);
    }
   
}
