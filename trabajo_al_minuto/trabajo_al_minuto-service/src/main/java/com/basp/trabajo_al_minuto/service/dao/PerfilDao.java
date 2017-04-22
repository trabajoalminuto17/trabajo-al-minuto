/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.dao;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.PERSISTENCE_SERVICE;
import com.basp.trabajo_al_minuto.model.business.BusinessPersistence;
import static com.basp.trabajo_al_minuto.model.business.BusinessPersistence.JPQL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_PERFILES_BY_EMPRESA;
import com.basp.trabajo_al_minuto.model.dto.PersistenceObject;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Perfil;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author BASP
 */
public class PerfilDao {

    private final BusinessPersistence BP;

    protected PerfilDao() {
        BP = new BusinessPersistence(Persistence.createEntityManagerFactory(PERSISTENCE_SERVICE));
    }

    protected List<Perfil> _getPerfilesByEmpresa(Long id) throws Exception {
        return BP.read(new PersistenceObject(Oferta.class, GET_PERFILES_BY_EMPRESA, JPQL, id));
    }

    protected Perfil _findPerfil(Long pk) throws Exception {
        return (Perfil) BP.find(Perfil.class, pk);
    }

    protected Perfil _updatePerfil(Perfil p) throws Exception {
        return (Perfil) BP.update(p);
    }

}
