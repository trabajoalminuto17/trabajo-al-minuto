/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.dao;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.PERSISTENCE_SERVICE;
import com.basp.trabajo_al_minuto.model.business.BusinessPersistence;
import static com.basp.trabajo_al_minuto.model.business.BusinessPersistence.JPQL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.ACTIVAR_PRUEBA;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_CITACIONES_ACTIVAS_BY_EMPRESA;
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

    /**
     * Actualiza la informacion de la citacion*
     */
    protected Citacion _updateCitacion(Citacion c) throws Exception {
        return (Citacion) BP.update(c);
    }

    /**
     * Consulta la citacion que tiene asignada una oferta, recibe como parametro
     * la pk de la tabla intermedia (usuarioHasOferta) *
     */
    protected Citacion _getCitacionByOferta(Long id) throws Exception {
        return (Citacion) BP.readOne(new PersistenceObject(UsuarioHasOferta.class, GET_CITACIONES_BY_OFERTA, JPQL, id));
    }

    /**
     * Retorna la información de una citación, recibe como parametro la pk de la
     * tabla*
     */
    protected Citacion _findCitacion(Long pk) throws Exception {
        return (Citacion) BP.find(Citacion.class, pk);
    }

    /**
     * Consulta la evaluacion que tiene asignada una citacion, recibe como
     * parametro la pk de la tabla citacion **
     */
    protected Evaluacion _getEvaluacionByCitacion(Long id) throws Exception {
        return (Evaluacion) BP.readOne(new PersistenceObject(Evaluacion.class, GET_EVALUACION_BY_CITACION, JPQL, id));
    }

    /**
     * Retorna todas la citaciones activas que tiene asignadas un usuario *
     */
    protected List<Citacion> _getCitacionesActivasByUsuario(Long id) throws Exception {
        return BP.read(new PersistenceObject(UsuarioHasOferta.class, GET_CITACIONES_ACTIVAS_BY_USUARIO, JPQL, id));
    }

    /**
     * Retorna todas la citaciones activas creadas por la empresa
     */
    protected List<Citacion> _getCitacionesActivasByEmpresa(Long id) throws Exception {
        return BP.read(new PersistenceObject(UsuarioHasOferta.class, GET_CITACIONES_ACTIVAS_BY_EMPRESA, JPQL, id));
    }

    /**
     * Cambia el estado de FALSE a TRUE de una prueba que tenga asignada un
     * participante, recibe como parametro el id la citación
     */
    protected Integer activarPruebas(Long id) throws Exception {
        return BP.readExecuted(new PersistenceObject(Citacion.class, ACTIVAR_PRUEBA, JPQL, id));
    }

}
