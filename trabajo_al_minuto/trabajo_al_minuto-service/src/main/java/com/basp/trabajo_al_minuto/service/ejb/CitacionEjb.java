/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.ejb;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dao.CitacionDao;
import com.basp.trabajo_al_minuto.service.entity.Citacion;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.facade.CitacionFacade;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author BASP
 */
@Stateless
@LocalBean
public class CitacionEjb extends CitacionDao implements CitacionFacade {

    @Override
    public Citacion updateCitacion(Citacion c) throws BusinessException {
        try {
            return _updateCitacion(c);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }
    
    @Override
    public List<Citacion> getCitacionByOferta(Long id) throws BusinessException {
        try {
            return _getCitacionByOferta(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Citacion findCitacion(Long pk) throws BusinessException {
        try {
            return _findCitacion(pk);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Evaluacion getEvaluacionByCitacion(Long id) throws BusinessException {
        try {
            return _getEvaluacionByCitacion(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public List<Citacion> getCitacionesActivasByUsuario(Long id) throws BusinessException {
        try {
            return _getCitacionesActivasByUsuario(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

}
