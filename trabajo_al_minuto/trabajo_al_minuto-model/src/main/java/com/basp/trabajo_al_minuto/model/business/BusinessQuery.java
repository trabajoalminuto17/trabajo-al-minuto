/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.business;

/**
 *
 * @author BASP
 */
public class BusinessQuery {

    public static final String GET_USUARIO_BY_EMAIL = "SELECT u FROM Usuario u WHERE LOWER(u.email) = :arg";
    public static final String GET_MENUS_BY_ROL = "SELECT r.menu FROM RolHasMenu r WHERE r.rol.rolId = :arg AND r.estado = TRUE AND r.rol.estado = TRUE AND r.menu.estado = TRUE AND r.menu.menuPadre.estado = TRUE ORDER BY r.menu.menuPadre.menuId, r.menu.menuId";
    public static final String GET_OFERTAS_BY_EMPRESA = "SELECT o FROM Oferta o WHERE o.usuarioAutor.empresa.empresaId = :arg";
    public static final String GET_USUARIO_BY_EMPRESA = "SELECT u FROM Usuario u WHERE u.empresa.empresaId = :arg";
    public static final String GET_CITACIONES_BY_OFERTA = "SELECT u.citacionList FROM UsuarioHasOferta u WHERE u.ofertasOfertaId.ofertaId = :arg";
    public static final String GET_EVALUACION_BY_CITACION = "SELECT e FROM Evaluacion e WHERE e.citacion.citacionId = :arg";
    public static final String GET_CITACIONES_ACTIVAS_BY_USUARIO = "SELECT u.citacionList FROM UsuarioHasOferta u WHERE u.usuarioUsuarioId.usuarioId = :arg AND u.estado = TRUE";
    public static final String GET_PERFILES_BY_EMPRESA = "SELECT o.perfil FROM Oferta o WHERE o.usuarioAutor.empresa.empresaId = :arg";
    public static final String GET_PRUEBAS_BY_PERFIL = "SELECT p.prueba FROM PerfilHasPrueba p WHERE p.perfil.perfilId = :arg";
    public static final String GET_USUARIOS_BY_OFERTA = "SELECT u.usuarioUsuarioId FROM UsuarioHasOferta u WHERE u.ofertasOfertaId.ofertaId = :arg";
    public static final String GET_OFERTAS_MAS_APLICADAS_BY_EMPRESA = "SELECT u.ofertasOfertaId, COUNT(u.usuarioUsuarioId) AS c FROM UsuarioHasOferta u WHERE u.ofertasOfertaId.usuarioAutor.empresa.empresaId = :arg GROUP BY u.ofertasOfertaId ORDER BY c";
    public static final String GET_USUARIOS_MEJORES_RESULTADOS = "SELECT e FROM Evaluacion e WHERE e.citacion.usuarioAutor.empresa.empresaId = :arg ORDER BY e.porcentaje DESC";
}
