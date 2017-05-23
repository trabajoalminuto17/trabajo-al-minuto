/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.facade;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dte.OfertaAplicada;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import java.util.List;

   /**
     * Los metodos contenidos en esta clase son los encargados de delegar las
     * peticiones hechas por el cliente y permite que más de una clase pueda
     * acceder a estos metodos
     *
     */
public interface OfertaFacade {

    public List<Oferta> getOfertasByEmpresa(Long id) throws BusinessException;

    public Oferta findOferta(Long pk) throws BusinessException;

    public Oferta updateOferta(Oferta o) throws BusinessException;

    public List<OfertaAplicada> getOfertasMasAplicadasByEmpresa(Long id) throws BusinessException;

    public List<OfertaAplicada> getOfertasMasAplicadas() throws BusinessException;

    public List<Oferta> getOfertasActivas() throws BusinessException;

    public List<Oferta> getOfertasExternal(Long area, List<String> palabras) throws BusinessException;

    public List<Oferta> getMisOfertas(Long id) throws BusinessException;

}
