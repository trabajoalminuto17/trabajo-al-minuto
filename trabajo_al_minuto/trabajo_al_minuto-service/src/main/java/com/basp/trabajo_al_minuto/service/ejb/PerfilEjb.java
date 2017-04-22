/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.ejb;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dao.PerfilDao;
import com.basp.trabajo_al_minuto.service.entity.Perfil;
import com.basp.trabajo_al_minuto.service.facade.PerfilFacade;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author BASP
 */
@Stateless
@LocalBean
public class PerfilEjb extends PerfilDao implements PerfilFacade {

    @Override
    public List<Perfil> getPerfilesByEmpresa(Long id) throws BusinessException {
        try {
            return _getPerfilesByEmpresa(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Perfil findPerfil(Long pk) throws BusinessException {
        try {
            return _findPerfil(pk);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Perfil updatePerfil(Perfil p) throws BusinessException {
        try {
            return _updatePerfil(p);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

}
