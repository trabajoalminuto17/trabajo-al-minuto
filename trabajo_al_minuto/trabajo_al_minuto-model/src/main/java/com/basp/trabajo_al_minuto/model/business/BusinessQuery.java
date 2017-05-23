/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.business;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * En esta clase se almacenan todos los querys personalizados necesarios para
 * generar la informacion
 */
public class BusinessQuery {

    public static final String GET_USUARIO_BY_EMAIL = "SELECT u FROM Usuario u WHERE LOWER(u.email) = :arg";
    public static final String GET_MENUS_BY_ROL = "SELECT r.menu FROM RolHasMenu r WHERE r.rol.rolId = :arg AND r.estado = TRUE AND r.rol.estado = TRUE AND r.menu.estado = TRUE AND r.menu.menuPadre.estado = TRUE ORDER BY r.menu.menuPadre.menuId, r.menu.menuId";
    public static final String GET_OFERTAS_BY_EMPRESA = "SELECT o FROM Oferta o WHERE o.usuarioAutor.empresa.empresaId = :arg";
    public static final String GET_USUARIO_BY_EMPRESA = "SELECT u FROM Usuario u WHERE u.empresa.empresaId = :arg";
    public static final String GET_CITACIONES_BY_OFERTA = "SELECT u.citacionId FROM UsuarioHasOferta u WHERE u.usuarioHasOfertaId = :arg";
    public static final String GET_EVALUACION_BY_CITACION = "SELECT e FROM Evaluacion e WHERE e.citacion.citacionId = :arg";
    public static final String GET_CITACIONES_ACTIVAS_BY_USUARIO = "SELECT u.citacion FROM UsuarioHasOferta u WHERE u.usuarioUsuarioId.usuarioId = :arg AND u.citacion.estado = TRUE ORDER BY u.fechaPostulacion";
    public static final String GET_CITACIONES_ACTIVAS_BY_EMPRESA = "SELECT u.citacion FROM UsuarioHasOferta u WHERE u.ofertasOfertaId.usuarioAutor.empresa.empresaId = :arg AND u.citacion.estado = TRUE ORDER BY u.fechaPostulacion";
    public static final String GET_PERFILES_BY_EMPRESA = "SELECT o.perfil FROM Oferta o WHERE o.usuarioAutor.empresa.empresaId = :arg";
    public static final String GET_PRUEBAS_BY_PERFIL = "SELECT p.prueba FROM PerfilHasPrueba p WHERE p.perfil.perfilId = :arg";
    public static final String GET_USUARIOS_BY_OFERTA = "SELECT u FROM UsuarioHasOferta u WHERE u.ofertasOfertaId.ofertaId = :arg";
    public static final String GET_OFERTAS_MAS_APLICADAS_BY_EMPRESA = "SELECT u.ofertasOfertaId, COUNT(u.usuarioUsuarioId) AS c FROM UsuarioHasOferta u WHERE u.ofertasOfertaId.usuarioAutor.empresa.empresaId = :arg GROUP BY u.ofertasOfertaId ORDER BY c";
    public static final String GET_OFERTAS_MAS_APLICADAS = "SELECT u.ofertasOfertaId, COUNT(u.usuarioUsuarioId) AS c FROM UsuarioHasOferta u GROUP BY u.ofertasOfertaId ORDER BY c";
    public static final String GET_USUARIOS_MEJORES_RESULTADOS = "SELECT e FROM Evaluacion e WHERE e.citacion.usuarioAutor.empresa.empresaId = :arg ORDER BY e.porcentaje DESC";
    public static final String GET_PRUEBAS_PLANTILLA_BY_EMPRESA = "SELECT p FROM PruebaPlantilla p WHERE p.empresaEmpresaId.empresaId = :arg";
    public static final String GET_CATALOGOS_BY_PADRE = "SELECT c FROM Catalogo c WHERE c.catalogoPadre.catalogoId = :arg AND c.estado = TRUE AND c.catalogoPadre.estado = TRUE";
    public static final String GET_OFERTAS_ACTIVAS = "SELECT o FROM Oferta o WHERE o.estado = TRUE ORDER BY o.fechaExpiracion";
    public static final String GET_USUARIO_HAS_OFERTA = "SELECT u FROM UsuarioHasOferta u WHERE u.usuarioUsuarioId.usuarioId = :arg0 AND u.ofertasOfertaId.ofertaId = :arg1";
    public static final String GET_MIS_OFERTAS = "SELECT u.ofertasOfertaId FROM UsuarioHasOferta u WHERE u.usuarioUsuarioId.usuarioId = :arg AND u.estado.catalogoId IN (8, 11) ORDER BY u.fechaPostulacion";
    public static final String ACTIVAR_PRUEBA = "UPDATE Citacion c SET c.activarPruebas = TRUE WHERE c.citacionId = :arg";

    /**
     * Mediante el uso de query nativo consulta todas ofertas disponibles según
     * los parametros ingresados (área o palabra clave) *
     */
    public static final List<Object> GET_OFERTAS_EXTERNAL(Long area, String palabra) {
        List<Object> response = new ArrayList();
        List<Object> objs = new ArrayList();
        StringBuilder sb = new StringBuilder("SELECT o FROM Oferta o WHERE o.estado = TRUE");
        if (area > 0 && palabra == null) {
            sb.append(" AND o.perfil.area.catalogoId = :arg0");
            objs.add(area);
        } else if (palabra != null && area <= 0) {
            sb.append(" AND LOWER(o.perfil.titulo) LIKE CONCAT('%',:arg0,'%')");
            objs.add(palabra);
        } else {
            sb.append(" AND o.perfil.area.catalogoId = :arg0 OR o.estado = TRUE AND LOWER(o.perfil.titulo) LIKE CONCAT('%',:arg1,'%')");
            objs.add(area);
            objs.add(palabra);
        }
        response.add(0, sb.toString());
        response.add(1, objs.toArray());
        return response;
    }
}
