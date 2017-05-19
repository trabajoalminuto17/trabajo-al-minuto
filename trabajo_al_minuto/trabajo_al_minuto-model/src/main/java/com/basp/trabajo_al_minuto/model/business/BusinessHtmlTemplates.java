/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.business;

import java.util.Date;

/**
 *
 * @author BASP
 */
public class BusinessHtmlTemplates {

    public static String restourarClaveTemplate(String password, String name, String email) throws BusinessException {
        StringBuilder response = new StringBuilder();
        response.append("<center><table width='800' border='0' cellspacing='0' cellpadding='5'>");
        response.append("<tbody><tr><td align='center'><table width='800' border='0' cellspacing='0' cellpadding='0'>");
        response.append("<tbody><tr><td width='800' style='border-bottom: solid 1px #ccc;'><img src='http://158.69.206.240:8080/trabajo_al_minuto-web/resources/image/logo_h.png' width='300' height='100' alt='Trabajo al minuto' style='float:right;'/></td></tr>");
        response.append("<tr><td bgcolor='#FFFFFF'><table width='800' border='0' cellpadding='10' cellspacing='0'>");
        response.append("<tbody><tr><td style='border-bottom: solid 1px #ccc;' width='923'>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Hola, ").append(name).append(". </p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> La contraseña de tu cuenta de Trabajo al minuto : <strong>").append(email).append("</strong> ha sido restaurada.</p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Ingresa a la plataforma con tu usuario y la siguiente contraseña: <strong>").append(password).append("</strong></p><br>");
        response.append("</td></tr></tbody></table></td></tr>");
        response.append("<tr><td style='border-bottom: solid 1px #ccc;' width='759' >");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'><strong>¿Preguntas?</strong> Contáctenos a nuestro correo soporte@trabajo_al_minuto.com.</p>");
        response.append("<br><br>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'>Por favor NO responda este mensaje, éste es solo informativo.</p>");
        response.append("</td></tr>");
        response.append("</tbody></table></center>");

        return response.toString();
    }

    public static String enviarCitacion(String empresa, Date fecha, String name, String lugar, String detalles, String tituloOferta, String telefono) throws BusinessException {
        StringBuilder response = new StringBuilder();
        response.append("<center><table width='800' border='0' cellspacing='0' cellpadding='5'>");
        response.append("<tbody><tr><td align='center'><table width='800' border='0' cellspacing='0' cellpadding='0'>");
        response.append("<tbody><tr><td width='800' style='border-bottom: solid 1px #ccc;'><img src='http://158.69.206.240:8080/trabajo_al_minuto-web/resources/image/logo_h.png' width='300' height='100' alt='Trabajo al minuto' style='float:right;'/></td></tr>");
        response.append("<tr><td bgcolor='#FFFFFF'><table width='800' border='0' cellpadding='10' cellspacing='0'>");
        response.append("<tbody><tr><td style='border-bottom: solid 1px #ccc;' width='923'>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Hola, ").append(name).append(". </p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Te informamos que has sido seleccionado por nuestra área de talento humano para continuar en el proceso de selección de: <strong>").append(tituloOferta).append("</strong>.</p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Los datos de tu citación son:</p> ");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Empresa: <strong>").append(empresa).append("</strong></p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Fecha: <strong>").append(fecha).append("</strong></p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Lugar: <strong>").append(lugar).append("</strong></p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Detalles: <strong>").append(detalles).append("</strong></p><br>");
        response.append("</td></tr></tbody></table></td></tr>");
        response.append("<tr><td style='border-bottom: solid 1px #ccc;' width='759' >");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'><strong>¿Preguntas?</strong> Puede comunicarse con nosotros al ").append(telefono).append("</p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'>o al correo: citaciones@trabajo_al_minuto.com.</p>");
        response.append("<br><br>");
        response.append("</td></tr>");
        response.append("</tbody></table></center>");

        return response.toString();
    }

    public static String enviarCitacion(String empresa, String fecha, String name, String lugar, String detalles, String tituloOferta) throws BusinessException {
        StringBuilder response = new StringBuilder();
        response.append("<center><table width='800' border='0' cellspacing='0' cellpadding='5'>");
        response.append("<tbody><tr><td align='center'><table width='800' border='0' cellspacing='0' cellpadding='0'>");
        response.append("<tbody><tr><td width='800' style='border-bottom: solid 1px #ccc;'><img src='http://158.69.206.240:8080/trabajo_al_minuto-web/resources/image/logo_h.png' width='300' height='100' alt='Trabajo al minuto' style='float:right;'/></td></tr>");
        response.append("<tr><td bgcolor='#FFFFFF'><table width='800' border='0' cellpadding='10' cellspacing='0'>");
        response.append("<tbody><tr><td style='border-bottom: solid 1px #ccc;' width='923'>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Hola, ").append(name).append(". </p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Te informamos que has sido seleccionado por nuestra área de talento humano para continuar en el proceso de selección de: <strong>").append(tituloOferta).append("</strong>.</p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Los datos de tu citación son:</p> ");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Empresa: <strong>").append(empresa).append("</strong></p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Fecha: <strong>").append(fecha).append("</strong></p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Lugar: <strong>").append(lugar).append("</strong></p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Detalles: <strong>").append(detalles).append("</strong></p><br>");
        response.append("</td></tr></tbody></table></td></tr>");
        response.append("<tr><td style='border-bottom: solid 1px #ccc;' width='759' >");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'><strong>¿Preguntas?</strong> Puede comunicarse con nosotros al 7456345 o al correo: citaciones@trabajo_al_minuto.com </p><br>");
        //response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'>o al correo: citaciones@trabajo_al_minuto.com.</p>");
        response.append("<br><br>");
        response.append("</td></tr>");
        response.append("</tbody></table></center>");

        return response.toString();
    }

    public static String actualizarCitacion(String empresa, String fecha, String name, String lugar, String detalles) throws BusinessException {
        StringBuilder response = new StringBuilder();
        response.append("<center><table width='800' border='0' cellspacing='0' cellpadding='5'>");
        response.append("<tbody><tr><td align='center'><table width='800' border='0' cellspacing='0' cellpadding='0'>");
        response.append("<tbody><tr><td width='800' style='border-bottom: solid 1px #ccc;'><img src='http://158.69.206.240:8080/trabajo_al_minuto-web/resources/image/logo_h.png' width='300' height='100' alt='Trabajo al minuto' style='float:right;'/></td></tr>");
        response.append("<tr><td bgcolor='#FFFFFF'><table width='800' border='0' cellpadding='10' cellspacing='0'>");
        response.append("<tbody><tr><td style='border-bottom: solid 1px #ccc;' width='923'>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Hola, ").append(name).append(". </p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Queremos informarte que la citación ha sido reprogramada, pedimos disculpas por los inconvenientes que esto pueda ocasionar.");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Los nuevos datos de tu citación son:</p> ");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Empresa: <strong>").append(empresa).append("</strong></p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Fecha: <strong>").append(fecha).append("</strong></p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Lugar: <strong>").append(lugar).append("</strong></p>");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'> Detalles: <strong>").append(detalles).append("</strong></p><br>");
        response.append("</td></tr></tbody></table></td></tr>");
        response.append("<tr><td style='border-bottom: solid 1px #ccc;' width='759' >");
        response.append("<p style=' color:#4D4D4D; font-family:Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif'><strong>¿Preguntas?</strong> Puede comunicarse con nosotros al 7456345 o al correo: citaciones@trabajo_al_minuto.com </p><br>");
        response.append("<br><br>");
        response.append("</td></tr>");
        response.append("</tbody></table></center>");

        return response.toString();
    }

}
