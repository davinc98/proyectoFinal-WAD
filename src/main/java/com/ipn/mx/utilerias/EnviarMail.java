/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author leoj_
 */
public class EnviarMail {
    public void enviarCorreo(String destinatario, String asunto, String mensaje){
        try {
            Properties p = new Properties();
            
            p.setProperty("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", "correonotificacioneswad@gmail.com");
            p.setProperty("mail.smtp.auth", "true");
            
            Session s = Session.getDefaultInstance(p);
            MimeMessage elMensaje = new MimeMessage(s);
            elMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
//            elMensaje.addRecipient(Message.RecipientType.CC, new InternetAddress("leojjosepf@gmail.com"));
            elMensaje.setSubject(asunto);
            elMensaje.setText(mensaje);
            
            Transport t = s.getTransport("smtp");
            t.connect(p.getProperty("user"), "Claveadmin12345$");
            t.sendMessage(elMensaje, elMensaje.getAllRecipients());
            t.close();
            
            //REQUISITOS
            //Entrar: a myaccount.google.com/lesssecureapps
            //Con la cuenta creada para este ejercicio
            //Activar el acceso a aplicaciones poco seguras
            
        } catch (AddressException ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void main (String[] args){
        EnviarMail email = new EnviarMail();
        String destinatario = "leoj_josepf@hotmail.com";
        String asunto = "Registro Satisfactorio";
        String texto = "Este es el mensaje!";
        
        email.enviarCorreo(destinatario, asunto, texto);
        
    }
}
