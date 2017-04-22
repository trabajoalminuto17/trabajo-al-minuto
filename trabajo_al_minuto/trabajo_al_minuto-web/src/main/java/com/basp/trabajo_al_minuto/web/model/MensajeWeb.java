/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.model;

import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.CONTACT_ADMIN_MESSAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.ERROR_MESSAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.EXITO_MESSAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.INTENTE_DE_NUEVO_MESSAGE;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

/**
 *
 * @author BASP
 */
public enum MensajeWeb {

    ACCESO_DENEGADO("Acceso denegado!", CONTACT_ADMIN_MESSAGE, FacesMessage.SEVERITY_ERROR),
    CREDENCIALES_INCORRECTAS("Credenciales Incorrectas", INTENTE_DE_NUEVO_MESSAGE, FacesMessage.SEVERITY_ERROR),
    USUARIO_NO_ENCONTRADO(ERROR_MESSAGE, "Usuario no encontrado, ".concat(INTENTE_DE_NUEVO_MESSAGE), FacesMessage.SEVERITY_ERROR),
    NO_PASSWORD(ERROR_MESSAGE, "No se ha podido cambiar la contraseña, ".concat(INTENTE_DE_NUEVO_MESSAGE), FacesMessage.SEVERITY_ERROR),
    CLAVE_RESTAURAD_NOT(ERROR_MESSAGE, "No se ha podido restaurar la contraseña, ".concat(INTENTE_DE_NUEVO_MESSAGE), FacesMessage.SEVERITY_ERROR),
    CLAVE_RESTAURADA_OK(EXITO_MESSAGE, "Se ha enviado la contraseña de restauración al email registrado.", FacesMessage.SEVERITY_INFO);

    private final String TITULO;
    private final String DESCRIPCION;
    private final Severity SEVERITY;

    private MensajeWeb(String title, String description, Severity severity) {
        this.TITULO = title;
        this.DESCRIPCION = description;
        this.SEVERITY = severity;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public String getTITULO() {
        return TITULO;
    }

    public Severity getSEVERITY() {
        return SEVERITY;
    }
}