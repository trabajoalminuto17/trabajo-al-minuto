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
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Opcion;
import com.basp.trabajo_al_minuto.service.entity.OpcionPlantilla;
import com.basp.trabajo_al_minuto.service.entity.PerfilHasPrueba;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.entity.PruebaPlantilla;
import com.basp.trabajo_al_minuto.service.entity.Respuesta;
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

    /**
     * Retorna todas las pruebas que la empresa ha creado *
     */
    protected List<Prueba> _getPruebasByEmpresa(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Retorna la informacion de una prueba, recibe como parametro la pk de la
     * tabla *
     */
    protected Prueba _findPrueba(Long pk) throws Exception {
        return (Prueba) BP.find(Prueba.class, pk);
    }

    /**
     * Actualiza la informacion de una prueba recibe como parametro el objeto
     * Prueba*
     */
    protected Prueba _updatePrueba(Prueba p) throws Exception {
        return (Prueba) BP.update(p);
    }

    /**
     * Retorna todas las pruebas que están asignadas a un perfil, recibe como
     * parametro el id del perfil
     */
    protected List<Prueba> _getPruebasByPerfil(Long id) throws Exception {
        return BP.read(new PersistenceObject(PerfilHasPrueba.class, GET_PRUEBAS_BY_PERFIL, JPQL, id));
    }

    /**
     * Retorna todas las pruebas tipo plantilla que están creadas, recibe como
     * parametro el id de la empresa *
     */
    protected List<PruebaPlantilla> _getPruebasPlantillaByEmpresa(Long id) throws Exception {
        return BP.read(new PersistenceObject(PruebaPlantilla.class, GET_PRUEBAS_PLANTILLA_BY_EMPRESA, JPQL, id));
    }

    /**
     * Retorna la informacion de una pruebas tipo plantilla que está creada,
     * recibe como parametro el id de la empresa *
     */
    protected PruebaPlantilla _findPruebaPlantilla(Long id) throws Exception {
        return (PruebaPlantilla) BP.find(PruebaPlantilla.class, id);
    }

    /**
     * Actualiza la informacion de una plantilla de prueba, recibe como
     * parametro el objeto PruebaPlantilla *
     */
    protected PruebaPlantilla _updatePruebaPlantilla(PruebaPlantilla p) throws Exception {
        return (PruebaPlantilla) BP.update(p);
    }

    /**
     * Elimina la opcion creada en la tabla OpcionPlantilla, devuelve un
     * booleano el cual indica si hubo exito o no en el proceso *
     */
    protected Boolean _removeOpcionPlantilla(Long id) throws Exception {
        return BP.delete(OpcionPlantilla.class, id);
    }

    /**
     * Acualiza la informaciond de una evaluacion, recibe como parametro el
     * Objeto Evaluacion
     */
    protected Evaluacion _updateEvaluacion(Evaluacion e) throws Exception {
        return (Evaluacion) BP.update(e);
    }

    /**
     * Retorna la informacion de una evaluacion en especifico, recibe como
     * parametro el id de la evaluacion
     */
    protected Evaluacion _findEvaluacion(Long id) throws Exception {
        return (Evaluacion) BP.find(Evaluacion.class, id);
    }

    /**
     * Acualiza la informaciond de una opcion, recibe como parametro el Objeto
     * Opcion
     */
    protected Opcion _updateOpcion(Opcion o) throws Exception {
        return (Opcion) BP.update(o);
    }

    /**
     * Acualiza la informacion de una respuesta, recibe como parametro el Objeto
     * Respuesta
     */
    protected Respuesta _updateRespuesta(Respuesta r) throws Exception {
        return (Respuesta) BP.update(r);
    }
}
