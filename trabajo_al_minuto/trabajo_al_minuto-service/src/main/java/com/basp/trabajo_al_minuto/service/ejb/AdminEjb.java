/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.ejb;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dao.AdminDao;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.facade.AdminFacade;
import java.util.List;

/**
 *
 * @author BASP
 */
public class AdminEjb extends AdminDao implements AdminFacade {

    @Override
    public List<Catalogo> getCatalogosByParent(Long id) throws BusinessException {
        try {
            return _getCatalogosByParent(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
    }

}
