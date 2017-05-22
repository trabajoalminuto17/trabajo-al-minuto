/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.facade;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Opcion;
import com.basp.trabajo_al_minuto.service.entity.OpcionPlantilla;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.entity.PruebaPlantilla;
import java.util.List;

/**
 *
 * @author BASP
 */
public interface PruebaFacade {

    public List<Prueba> getPruebasByEmpresa(Long id) throws BusinessException;

    public Prueba findPrueba(Long pk) throws BusinessException;

    public Prueba updatePrueba(Prueba p) throws BusinessException;

    public List<Prueba> getPruebasByPerfil(Long id) throws BusinessException;

    public List<PruebaPlantilla> getPruebasPlantillaByEmpresa(Long id) throws BusinessException;

    public PruebaPlantilla findPruebaPlantilla(Long id) throws BusinessException;

    public PruebaPlantilla updatePruebaPlantilla(PruebaPlantilla p) throws BusinessException;

    public Boolean removeOpcionPlantilla(Long id) throws BusinessException;

    public Evaluacion updateEvaluacion(Evaluacion e) throws BusinessException;

    public Evaluacion findEvaluacion(Long id) throws BusinessException;

    public Opcion updateOpcion(Opcion o) throws BusinessException;
}
