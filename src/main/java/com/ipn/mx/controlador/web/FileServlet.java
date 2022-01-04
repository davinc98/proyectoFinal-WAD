/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.entidades.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author leoj_
 */
@MultipartConfig
@WebServlet(name = "FileServlet", urlPatterns = {"/FileServlet"})
public class FileServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FileServlet.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        usuario = dao.read(usuario);
        
        String accion = request.getParameter("accion");
        RequestDispatcher rd = request.getRequestDispatcher("redireccionaToLogin.jsp");
        
        if(accion.equals("guardar")){
            usuario.setImagen(almacenarImagen(request, response));
            rd = request.getRequestDispatcher("redireccionaToLogin.jsp");
            dao.update(usuario);
        }else if(accion.equals("omitir")){
            usuario.setImagen(getPathImageDefault(request, response));
            rd = request.getRequestDispatcher("redireccionaToLogin.jsp");
            dao.update(usuario);
        }else if(accion.equals("update")){
            usuario.setImagen(almacenarImagen(request, response));
            rd = request.getRequestDispatcher("redireccionaToListaUsuarios.jsp");
            dao.update(usuario);
        }else if(accion.equals("cancel")){
            rd = request.getRequestDispatcher("redireccionaToListaUsuarios.jsp");
        }
   
        rd.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String almacenarImagen(HttpServletRequest request, HttpServletResponse response) {
        String pathFiles = request.getRealPath("/");
        File uploads = new File(pathFiles);
        String fileName="";

        //Recuperar la url de la app: https://www.it-swarm-es.com/es/jsp/como-obtener-la-url-del-dominio-y-el-nombre-de-la-aplicacion/968427622/
        String pathConexion = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        logger.log(Level.INFO, pathConexion);

        //RECUPERAR Y ALMACENAR LA IMAGEN=======================================
        try {
            Part part = request.getPart("fileImagen");
            if (part != null) {
                if (esExtensionImagen(part.getSubmittedFileName())) {//Verifica ext

                    //Almacenar imagen en el sistema de archivos
                    fileName = guardarArchivo(part, uploads, getUniqueName());

                } else {

                }
            } else {

            }
        } catch (IOException ex) {
            Logger.getLogger(FileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(FileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pathConexion+"faces/images/"+fileName;
    }

    private String guardarArchivo(Part part, File pathUploads, String nombreImg) {
        String pathAbsolute = "";
        String fileName = "";

        try {
            Path path = Paths.get(part.getSubmittedFileName());
            String fileNameExt = path.getFileName().toString();
            int i = fileNameExt.lastIndexOf('.');

            String ext = "";
            ext = fileNameExt.substring(i);

            fileName = "img-USR-" + nombreImg + ext;
            InputStream input = part.getInputStream();

            if (input != null) {
                File file = new File(pathUploads + "/images", fileName);
                pathAbsolute = file.getAbsolutePath();
                Files.copy(input, file.toPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return pathAbsolute;
        return fileName;
    }

    private boolean esExtensionImagen(String fileName) {
        String[] extenciones = {".ico", ".png", ".jpg", ".jpeg"};
        for (String ext : extenciones) {
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    private String getUniqueName() {
        String middle = "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        middle = sdf.format(new Date());

        return middle;
    }
    
    private String getPathImageDefault(HttpServletRequest request, HttpServletResponse response) {
        String pathFiles = request.getRealPath("/");
        File uploads = new File(pathFiles);

        //Recuperar la url de la app: https://www.it-swarm-es.com/es/jsp/como-obtener-la-url-del-dominio-y-el-nombre-de-la-aplicacion/968427622/
        String pathConexion = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        logger.log(Level.INFO, pathConexion);

        return pathConexion+"faces/images/user.png";
    }
}
