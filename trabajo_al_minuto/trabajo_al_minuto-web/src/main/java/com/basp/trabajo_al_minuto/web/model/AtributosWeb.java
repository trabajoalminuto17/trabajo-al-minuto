/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.model;

/**
 *
 * @author BASP
 */
public class AtributosWeb {

    private static final String REDIRECT = "?faces-redirect=true";
    public static final String FACES_PATH = "/trabajo_al_minuto-web/";

    public static final String CONTACT_ADMIN_MESSAGE = "Por favor contácte un administrador de la aplicación para más detalles.";
    public static final String INTENTE_DE_NUEVO_MESSAGE = "Intente de nuevo.";
    public static final String EXITO_MESSAGE = "Éxito!";
    public static final String ERROR_MESSAGE = "Error!";
    public static final String ALERTA_MESSAGE = "Alerta!";

    public static final String INICIAR_SESSION_PAGE = FACES_PATH.concat("iniciar_sesion.xhtml").concat(REDIRECT);
    public static final String INICIO_PAGE = FACES_PATH.concat("inicio.xhtml").concat(REDIRECT);
    public static final String CLAVE_PAGE = FACES_PATH.concat("clave.xhtml").concat(REDIRECT);
    public static final String ERROR_PAGE = FACES_PATH.concat("error.xhtml").concat(REDIRECT);
    public static final String DETALLE_USUARIO_PAGE = FACES_PATH.concat("modulos/usuarios/detalle.xhtml").concat(REDIRECT);
    public static final String DETALLE_OFERTA_PAGE = FACES_PATH.concat("modulos/ofertas/detalle.xhtml").concat(REDIRECT);
    public static final String DETALLE_POSTULACION_PAGE = FACES_PATH.concat("modulos/ofertas/detalle_postulacion.xhtml").concat(REDIRECT);
    public static final String RUTA_HOJA_DE_VIDA_PDF = "D:\\hojas_de_vida\\";
}
