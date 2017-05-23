/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.facade;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Citacion;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import java.util.List;

/**
 *
 * @author BASP
 */
public interface CitacionFacade {

    /**
     * Los metodos contenidos en esta clase son los encargados de delegar las
     * peticiones hechas por el cliente y permite que más de una clase pueda
     * acceder a estos metodos
     *
     */
    public Citacion updateCitacion(Citacion c) throws BusinessException;

    public Citacion getCitacionByOferta(Long id) throws BusinessException;

    public Citacion findCitacion(Long pk) throws BusinessException;

    public Evaluacion getEvaluacionByCitacion(Long id) throws BusinessException;

    public List<Citacion> getCitacionesActivasByUsuario(Long id) throws BusinessException;

    public List<Citacion> getCitacionesActivasByEmpresa(Long id) throws BusinessException;

    public Integer activarPrueba(Long id) throws BusinessException;

}
