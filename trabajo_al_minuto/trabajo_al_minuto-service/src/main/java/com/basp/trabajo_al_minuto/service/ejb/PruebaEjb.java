/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.ejb;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dao.PruebaDao;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Opcion;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.entity.PruebaPlantilla;
import com.basp.trabajo_al_minuto.service.entity.Respuesta;
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

    @Override
    public List<PruebaPlantilla> getPruebasPlantillaByEmpresa(Long id) throws BusinessException {
        try {
            return _getPruebasPlantillaByEmpresa(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public PruebaPlantilla findPruebaPlantilla(Long id) throws BusinessException {
        try {
            return _findPruebaPlantilla(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public PruebaPlantilla updatePruebaPlantilla(PruebaPlantilla p) throws BusinessException {
        try {
            return _updatePruebaPlantilla(p);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Boolean removeOpcionPlantilla(Long id) throws BusinessException {
        try {
            return _removeOpcionPlantilla(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Evaluacion updateEvaluacion(Evaluacion e) throws BusinessException {
        try {
            return _updateEvaluacion(e);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Evaluacion findEvaluacion(Long id) throws BusinessException {
        try {
            return _findEvaluacion(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Opcion updateOpcion(Opcion o) throws BusinessException {
        try {
            return _updateOpcion(o);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Respuesta updateRespuesta(Respuesta r) throws BusinessException {
        try {
            return _updateRespuesta(r);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

}
