/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.dao;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.PERSISTENCE_SERVICE;
import com.basp.trabajo_al_minuto.model.business.BusinessPersistence;
import static com.basp.trabajo_al_minuto.model.business.BusinessPersistence.JPQL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_MENUS_BY_ROL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_USUARIOS_BY_OFERTA;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_USUARIOS_MEJORES_RESULTADOS;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_USUARIO_BY_EMAIL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_USUARIO_BY_EMPRESA;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_USUARIO_HAS_OFERTA;
import com.basp.trabajo_al_minuto.model.dto.PersistenceObject;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Menu;
import com.basp.trabajo_al_minuto.service.entity.RolHasMenu;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author BASP
 */
public class UsuarioDao {

    private final BusinessPersistence BP;

    protected UsuarioDao() {
        BP = new BusinessPersistence(Persistence.createEntityManagerFactory(PERSISTENCE_SERVICE));
    }

    /**
     * Retorna la informacion de un usuario según el email enviado*
     */
    protected Usuario _getUsuarioByEmail(String email) throws Exception {
        return (Usuario) BP.readOne(new PersistenceObject(Usuario.class, GET_USUARIO_BY_EMAIL, JPQL, email));
    }

    /**
     * Verifica si el usuario y contraseñas ingresados son correctos*
     */
    protected Boolean _validarCredenciales(Usuario u) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Actualiza la contraseña guardada*
     */
    protected Boolean _cambiarClave(Usuario u) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Retorna la informacion de los usuarios pertenecientes a una empresa,
     * recibe como parametro el id de la empresa *
     */
    protected List<Usuario> _getUsuariosByEmpresa(Long id) throws Exception {
        return BP.read(new PersistenceObject(Usuario.class, GET_USUARIO_BY_EMPRESA, JPQL, id));
    }

    /**
     * Retorna la informacion de un usuario, recibe como parametro la PK de la
     * tabla usuario *
     */
    protected Usuario _findUsuario(Long pk) throws Exception {
        return (Usuario) BP.find(Usuario.class, pk);
    }

    /**
     * Actualiza la informacion de un usuario, recibe como parametro el Objeto
     * usuario*
     */
    protected Usuario _updateUsuario(Usuario u) throws Exception {
        return (Usuario) BP.update(u);
    }

    /**
     * Inserta en la tabla Usuario la informacion de un nuevo usuario, recibe
     * como parametro el objeto Usuario*
     */
    protected void _createUsuario(Usuario u) throws Exception {
        BP.create(u);
    }

    /**
     * Lista todos los usuarios que se han postulado a una oferta*
     */
    protected List<UsuarioHasOferta> _getUsuariosByOferta(Long id) throws Exception {
        return BP.read(new PersistenceObject(UsuarioHasOferta.class, GET_USUARIOS_BY_OFERTA, JPQL, id));
    }

    /**
     * Actualiza la informacion de los usuarios que se han postulado a una
     * oferta *
     */
    protected UsuarioHasOferta _updateUsuarioHasOferta(UsuarioHasOferta uho) throws Exception {
        return (UsuarioHasOferta) BP.update(uho);
    }

    /**
     * Lista todos los menus asignados a un rol *
     */
    protected List<Menu> _getMenusByRol(Long id) throws Exception {
        return BP.read(new PersistenceObject(RolHasMenu.class, GET_MENUS_BY_ROL, JPQL, id));
    }

    /**
     * Lista todos aquellos usuarios que presentaron una prueba y que obtuvieron
     * buenos resultados*
     */
    protected List<Evaluacion> _getUsuariosMejoresResultadosByEmpresa(Long id) throws Exception {
        return BP.read(new PersistenceObject(Evaluacion.class, GET_USUARIOS_MEJORES_RESULTADOS, JPQL, id, 4));
    }

    /**
     * Lista la informacion de un usuario el cual se postulo a una oferta,
     * recibe como parametro la PK de la tabla UsuarioHasOferta*
     */
    protected UsuarioHasOferta _findUsuarioHasOferta(Long pk) throws Exception {
        return (UsuarioHasOferta) BP.find(UsuarioHasOferta.class, pk);
    }

    /**
     * Devuelve la informacion de un usuario que se postulo a una oferta según
     * usuario y la oferta
     */
    protected UsuarioHasOferta _getUsuarioHasOfertaByUsuarioAndOferta(Long usuario, Long oferta) throws Exception {
        return (UsuarioHasOferta) BP.readOne(new PersistenceObject(UsuarioHasOferta.class, GET_USUARIO_HAS_OFERTA, JPQL, new Object[]{usuario, oferta}));
    }
}
