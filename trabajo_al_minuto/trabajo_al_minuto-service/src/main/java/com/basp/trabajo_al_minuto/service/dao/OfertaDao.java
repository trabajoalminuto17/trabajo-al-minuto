/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.dao;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.PERSISTENCE_SERVICE;
import com.basp.trabajo_al_minuto.model.business.BusinessPersistence;
import static com.basp.trabajo_al_minuto.model.business.BusinessPersistence.JPQL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_MIS_OFERTAS;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_OFERTAS_ACTIVAS;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_OFERTAS_BY_EMPRESA;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_OFERTAS_EXTERNAL;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_OFERTAS_MAS_APLICADAS;
import static com.basp.trabajo_al_minuto.model.business.BusinessQuery.GET_OFERTAS_MAS_APLICADAS_BY_EMPRESA;
import com.basp.trabajo_al_minuto.model.dto.PersistenceObject;
import com.basp.trabajo_al_minuto.service.dte.OfertaAplicada;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    protected List<OfertaAplicada> _getOfertasMasAplicadas() throws Exception {
        List<Object[]> response = BP.read(new PersistenceObject(UsuarioHasOferta.class, GET_OFERTAS_MAS_APLICADAS, JPQL, 6));
        List<OfertaAplicada> list = new ArrayList();
        for (Object[] o : response) {
            list.add(new OfertaAplicada((Oferta) o[0], (Long) o[1]));
        }
        return list;
    }

    protected List<Oferta> _getOfertasActivas() throws Exception {
        return BP.read(new PersistenceObject(Oferta.class, GET_OFERTAS_ACTIVAS, JPQL));
    }

    protected List<Oferta> _getOfertasExternal(Long area, List<String> palabras) throws Exception {
        List<Oferta> response = new ArrayList();
        if (!palabras.isEmpty()) {
            for (String str : palabras) {
                List<Object> request = GET_OFERTAS_EXTERNAL(area, str.trim());
                response.addAll(BP.read(new PersistenceObject(Oferta.class, (String) request.get(0), JPQL, (Object[]) request.get(1))));
            }
            Set<Oferta> hs = new HashSet<>();
            hs.addAll(response);
            response.clear();
            response.addAll(hs);
            return response;
        } else {
            if (area > 0) {
                List<Object> request = GET_OFERTAS_EXTERNAL(area, null);
                return BP.read(new PersistenceObject(Oferta.class, (String) request.get(0), JPQL, (Object[]) request.get(1)));
            } else {
                return _getOfertasActivas();
            }
        }
    }

    protected List<Oferta> _getMisOfertas(Long id) throws Exception {
        return BP.read(new PersistenceObject(UsuarioHasOferta.class, GET_MIS_OFERTAS, JPQL, id));
    }
}
