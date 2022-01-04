<%-- 
    Document   :
    Created on : 
    Author     : 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <title>UploadImagen</title>
    </head>
    <body>
        <div class="container">
            <br/><br/><br/>

            <center>
                <div class="col-sm-5">
                    <div class="card bg-light">
                        <div class="card header">
                            Sibir Foto
                        </div>
                        <div class="card card-body">
                            <form method="post" action="FileServlet?accion=update" enctype="multipart/form-data">
                                <input type="text" id="txtIdUsuario" name="txtIdUsuario"
                                       value="<%= request.getParameter("idUsuario") %>" hidden="true"/>
                                <!--                        <p:fileUpload name="txtImagen" 
                                                                           id="txtImagen" mode="simple" skinSimple="true"/>-->
                                <img src="images/user.png" width="200"
                                         height="200"/>
                                <br/>
                                <input type="file" 
                                       name="fileImagen" 
                                       id="fileImagen" 
                                       placeholder="Subir imagen"
                                       maxlenght="100"
                                       value="Seleccionar imagen"
                                       class="form-control" />
                                <br/>
                                <button type="submit" class="btn btn-outline-primary d-block">Actualizar Imagen</button>   
                            </form>
                            <hr/>
                            <form method="post" action="FileServlet?accion=cancel" enctype="multipart/form-data">
                                <input type="text" id="txtIdUsuario" name="txtIdUsuario" 
                                       value="<%= request.getParameter("idUsuario") %>" hidden="true"/>
                                <button type="submit" class="btn btn-outline-primary d-block">Regresar</button>  
                            </form>

                        </div> 
                    </div>
                </div>
            </center>

        </div>
    </body>
</html>
