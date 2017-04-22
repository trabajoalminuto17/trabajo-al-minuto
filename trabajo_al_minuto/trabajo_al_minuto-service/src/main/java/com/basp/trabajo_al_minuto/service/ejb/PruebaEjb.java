/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.ejb;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dao.PruebaDao;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.facade.PruebaFacade;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author BASP
 */
@Stateless
@LocalBean
public class PruebaEjb extends PruebaDao implements PruebaFacade {

    @Override
    public List<Prueba> getPruebasByEmpresa(Long id) throws BusinessException {
        try {
            return _getPruebasByEmpresa(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Prueba findPrueba(Long pk) throws BusinessException {
        try {
            return _findPrueba(pk);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Prueba updatePrueba(Prueba p) throws BusinessException {
        try {
            return _updatePrueba(p);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public List<Prueba> getPruebasByPerfil(Long id) throws BusinessException {
        try {
            return _getPruebasByPerfil(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

}
