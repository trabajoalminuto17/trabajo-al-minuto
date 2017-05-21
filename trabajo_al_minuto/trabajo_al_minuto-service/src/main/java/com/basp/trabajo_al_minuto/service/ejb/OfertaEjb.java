/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.ejb;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dao.OfertaDao;
import com.basp.trabajo_al_minuto.service.dte.OfertaAplicada;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import com.basp.trabajo_al_minuto.service.facade.OfertaFacade;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author BASP
 */
@Stateless
@LocalBean
public class OfertaEjb extends OfertaDao implements OfertaFacade {

    @Override
    public List<Oferta> getOfertasByEmpresa(Long id) throws BusinessException {
        try {
            return _getOfertasByEmpresa(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Oferta findOferta(Long pk) throws BusinessException {
        try {
            return _findOferta(pk);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Oferta updateOferta(Oferta o) throws BusinessException {
        try {
            return _updateOferta(o);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public List<OfertaAplicada> getOfertasMasAplicadasByEmpresa(Long id) throws BusinessException {
        try {
            return _getOfertasMasAplicadasByEmpresa(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public List<OfertaAplicada> getOfertasMasAplicadas() throws BusinessException {
        try {
            return _getOfertasMasAplicadas();
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public List<Oferta> getOfertasActivas() throws BusinessException {
        try {
            return _getOfertasActivas();
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public List<Oferta> getOfertasExternal(Long area, List<String> palabras) throws BusinessException {
        try {
            return _getOfertasExternal(area, palabras);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

}
