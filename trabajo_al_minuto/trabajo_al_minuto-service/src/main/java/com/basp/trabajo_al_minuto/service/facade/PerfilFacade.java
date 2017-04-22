/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.facade;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Perfil;
import java.util.List;

/**
 *
 * @author BASP
 */
public interface PerfilFacade {

    public List<Perfil> getPerfilesByEmpresa(Long id) throws BusinessException;

    public Perfil findPerfil(Long pk) throws BusinessException;

    public Perfil updatePerfil(Perfil p) throws BusinessException;

}
