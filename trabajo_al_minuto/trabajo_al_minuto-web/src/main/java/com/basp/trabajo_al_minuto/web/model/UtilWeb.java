/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author BASP
 */
public class UtilWeb {

    public static FacesMessage webMessage(MensajeWeb me) {
        return new FacesMessage(me.getSEVERITY(), me.getTITULO(), me.getDESCRIPCION());
    }

    public static StreamedContent descargarArchivoPdf(String url) {
        File file = new File(url);
        if (file.exists()) {
            try {
                InputStream stream = new FileInputStream(file);
                return new DefaultStreamedContent(stream, "pdf", file.getName());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UtilWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
