/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.dao;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.PERSISTENCE_SERVICE;
import com.basp.trabajo_al_minuto.model.business.BusinessPersistence;
import static com.basp.trabajo_al_minuto.model.business.BusinessPersistence.JPQL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_CATALOGOS_BY_PADRE;
import com.basp.trabajo_al_minuto.model.dto.PersistenceObject;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author BASP
 */
public class AdminDao {

    private final BusinessPersistence BP;

    protected AdminDao() {
        BP = new BusinessPersistence(Persistence.createEntityManagerFactory(PERSISTENCE_SERVICE));
    }

    protected List<Catalogo> _getCatalogosByParent(Long id) throws Exception {
        return BP.read(new PersistenceObject(Catalogo.class, GET_CATALOGOS_BY_PADRE, JPQL, id));
    }

}
