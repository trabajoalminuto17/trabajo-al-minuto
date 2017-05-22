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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
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

    public static SelectItem propiedadesItem(SelectItem item) {
        item.setDisabled(false);
        item.setNoSelectionOption(true);
        return item;
    }

    public static String formatDate(LocalDateTime date) {
        String d = date.format(DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm", new Locale("es")));
        return Character.toString(d.charAt(0)).toUpperCase() + d.substring(1);
    }

    public static String formatDateTime(LocalDateTime date) {
        String d = date.format(DateTimeFormatter.ofPattern("HH:mm", new Locale("es")));
        return Character.toString(d.charAt(0)).toUpperCase() + d.substring(1);
    }

}
