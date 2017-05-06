/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.ejb;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dao.UsuarioDao;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Menu;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import com.basp.trabajo_al_minuto.service.facade.UsuarioFacade;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author BASP
 */
@Stateless
@LocalBean
public class UsuarioEjb extends UsuarioDao implements UsuarioFacade {

    @Override
    public Usuario getUsuarioByEmail(String email) throws BusinessException {
        try {
            return _getUsuarioByEmail(email);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Boolean validarCredenciales(Usuario u) throws BusinessException {
        try {
            return _validarCredenciales(u);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Boolean cambiarClave(Usuario u) throws BusinessException {
        try {
            return _cambiarClave(u);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public List<Usuario> getUsuariosByEmpresa(Long id) throws BusinessException {
        try {
            return _getUsuariosByEmpresa(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Usuario findUsuario(Long pk) throws BusinessException {
        try {
            return _findUsuario(pk);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Usuario updateUsuario(Usuario u) throws BusinessException {
        try {
            return _updateUsuario(u);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public void createUsuario(Usuario u) throws BusinessException {
        try {
            _createUsuario(u);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public List<Usuario> getUsuariosByOferta(Long id) throws BusinessException {
        try {
            return _getUsuariosByOferta(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public UsuarioHasOferta updateUsuarioHasOferta(UsuarioHasOferta uho) throws BusinessException {
        try {
            return _updateUsuarioHasOferta(uho);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public List<Menu> getMenusByRol(Long id) throws BusinessException {
        try {
            return _getMenusByRol(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public List<Evaluacion> getUsuariosMejoresResultadosByEmpresa(Long id) throws BusinessException {
        try {
            return _getUsuariosMejoresResultadosByEmpresa(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

}
