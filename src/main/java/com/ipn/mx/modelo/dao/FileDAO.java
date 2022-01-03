/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.primefaces.shaded.commons.io.IOUtils;

/**
 * Re-usable file implementations
 * @author leoj_
 */
public class FileDAO {

    public void save(InputStream inputstream, File file ) throws FileNotFoundException, IOException{
        OutputStream output = new FileOutputStream(file);
       
        //Copiar inputstream a output location
        IOUtils.copy(inputstream, output);
    }
}
