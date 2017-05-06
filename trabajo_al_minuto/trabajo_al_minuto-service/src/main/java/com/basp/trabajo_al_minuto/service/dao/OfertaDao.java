/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.dao;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.PERSISTENCE_SERVICE;
import com.basp.trabajo_al_minuto.model.business.BusinessPersistence;
import static com.basp.trabajo_al_minuto.model.business.BusinessPersistence.JPQL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_OFERTAS_BY_EMPRESA;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_OFERTAS_MAS_APLICADAS_BY_EMPRESA;
import com.basp.trabajo_al_minuto.model.dto.PersistenceObject;
import com.basp.trabajo_al_minuto.service.dte.OfertaAplicada;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author BASP
 */
public class OfertaDao {

    private final BusinessPersistence BP;

    protected OfertaDao() {
        BP = new BusinessPersistence(Persistence.createEntityManagerFactory(PERSISTENCE_SERVICE));
    }
//

    protected List<Oferta> _getOfertasByEmpresa(Long id) throws Exception {
        return BP.read(new PersistenceObject(Oferta.class, GET_OFERTAS_BY_EMPRESA, JPQL, id));
    }

    protected Oferta _findOferta(Long pk) throws Exception {
        return (Oferta) BP.find(Oferta.class, pk);
    }

    protected Oferta _updateOferta(Oferta o) throws Exception {
        return (Oferta) BP.update(o);
    }

    protected List<OfertaAplicada> _getOfertasMasAplicadasByEmpresa(Long id) throws Exception {
        List<Object[]> response = BP.read(new PersistenceObject(UsuarioHasOferta.class, GET_OFERTAS_MAS_APLICADAS_BY_EMPRESA, JPQL, id, 6));
        List<OfertaAplicada> list = new ArrayList();
        for (Object[] o : response) {
            list.add(new OfertaAplicada((Oferta) o[0], (Long) o[1]));
        }
        return list;
    }

}
