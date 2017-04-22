/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.dao;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.PERSISTENCE_SERVICE;
import com.basp.trabajo_al_minuto.model.business.BusinessPersistence;
import static com.basp.trabajo_al_minuto.model.business.BusinessPersistence.JPQL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_CITACIONES_ACTIVAS_BY_USUARIO;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_CITACIONES_BY_OFERTA;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_EVALUACION_BY_CITACION;
import com.basp.trabajo_al_minuto.model.dto.PersistenceObject;
import com.basp.trabajo_al_minuto.service.entity.Citacion;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author BASP
 */
public class CitacionDao {

    private final BusinessPersistence BP;

    protected CitacionDao() {
        BP = new BusinessPersistence(Persistence.createEntityManagerFactory(PERSISTENCE_SERVICE));
    }

    protected Citacion _updateCitacion(Citacion c) throws Exception {
        return (Citacion) BP.update(c);
    }

    protected List<Citacion> _getCitacionByOferta(Long id) throws Exception {
        return BP.read(new PersistenceObject(Citacion.class, GET_CITACIONES_BY_OFERTA, JPQL, id));
    }

    protected Citacion _findCitacion(Long pk) throws Exception {
        return (Citacion) BP.find(Citacion.class, pk);
    }

    protected Evaluacion _getEvaluacionByCitacion(Long id) throws Exception {
        return (Evaluacion) BP.readOne(new PersistenceObject(Evaluacion.class, GET_EVALUACION_BY_CITACION, JPQL, id));
    }

    protected List<Citacion> _getCitacionesActivasByUsuario(Long id) throws Exception {
        return BP.read(new PersistenceObject(UsuarioHasOferta.class, GET_CITACIONES_ACTIVAS_BY_USUARIO, JPQL, id));
    }

}
