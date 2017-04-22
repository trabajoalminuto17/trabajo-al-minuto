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
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_USUARIO_BY_EMAIL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_USUARIO_BY_EMPRESA;
import com.basp.trabajo_al_minuto.model.dto.PersistenceObject;
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

    protected Usuario _getUsuarioByEmail(String email) throws Exception {
        return (Usuario) BP.readOne(new PersistenceObject(Usuario.class, GET_USUARIO_BY_EMAIL, JPQL, email));
    }

    protected Boolean _validarCredenciales(Usuario u) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected Boolean _cambiarClave(Usuario u) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected List<Usuario> _getUsuariosByEmpresa(Long id) throws Exception {
        return BP.read(new PersistenceObject(Usuario.class, GET_USUARIO_BY_EMPRESA, JPQL, id));
    }

    protected Usuario _findUsuario(Long pk) throws Exception {
        return (Usuario) BP.find(Usuario.class, pk);
    }

    protected Usuario _updateUsuario(Usuario u) throws Exception {
        return (Usuario) BP.update(u);
    }

    protected List<Usuario> _getUsuariosByOferta(Long id) throws Exception {
        return BP.read(new PersistenceObject(UsuarioHasOferta.class, GET_USUARIOS_BY_OFERTA, JPQL, id));
    }

    protected UsuarioHasOferta _updateUsuarioHasOferta(UsuarioHasOferta uho) throws Exception {
        return (UsuarioHasOferta) BP.update(uho);
    }

    protected List<Menu> _getMenusByRol(Long id) throws Exception {
        return BP.read(new PersistenceObject(RolHasMenu.class, GET_MENUS_BY_ROL, JPQL, id));
    }

}
