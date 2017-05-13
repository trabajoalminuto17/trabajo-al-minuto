/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.dao;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.PERSISTENCE_SERVICE;
import com.basp.trabajo_al_minuto.model.business.BusinessPersistence;
import static com.basp.trabajo_al_minuto.model.business.BusinessPersistence.JPQL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_PRUEBAS_BY_PERFIL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_PRUEBAS_PLANTILLA_BY_EMPRESA;
import com.basp.trabajo_al_minuto.model.dto.PersistenceObject;
import com.basp.trabajo_al_minuto.service.entity.PerfilHasPrueba;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.entity.PruebaPlantilla;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author BASP
 */
public class PruebaDao {

    private final BusinessPersistence BP;

    protected PruebaDao() {
        BP = new BusinessPersistence(Persistence.createEntityManagerFactory(PERSISTENCE_SERVICE));
    }

    protected List<Prueba> _getPruebasByEmpresa(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected Prueba _findPrueba(Long pk) throws Exception {
        return (Prueba) BP.find(Prueba.class, pk);
    }

    protected Prueba _updatePrueba(Prueba p) throws Exception {
        return (Prueba) BP.update(p);
    }

    protected List<Prueba> _getPruebasByPerfil(Long id) throws Exception {
        return BP.read(new PersistenceObject(PerfilHasPrueba.class, GET_PRUEBAS_BY_PERFIL, JPQL, id));
    }

    protected List<PruebaPlantilla> _getPruebasPlantillaByEmpresa(Long id) throws Exception {
        return BP.read(new PersistenceObject(PruebaPlantilla.class, GET_PRUEBAS_PLANTILLA_BY_EMPRESA, JPQL, id));
    }

}
