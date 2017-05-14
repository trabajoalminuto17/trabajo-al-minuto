/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_OFERTA_PAGE;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class VerOfertasView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Oferta> ofertasFlitradas;
    private Oferta ofertaSeleccionada;

    @PostConstruct
    public void init() {
        try {
            if (getOfertaId() != null) {
                ofertaSeleccionada = ofertaEjb.findOferta(getOfertaId());
            }
        } catch (BusinessException ex) {
            Logger.getLogger(VerOfertasView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Oferta> getOfertasByEmpreas() {
        try {
            Usuario u = getUserLogin();
            return ofertaEjb.getOfertasByEmpresa(u.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(VerOfertasView.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public void onRowSelectVerOfertas(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ofertaId", ((Oferta) event.getObject()).getOfertaId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_OFERTA_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(VerOfertasView.class.getName()).log(Level.SEVERE, "onRowSelectVerOfertas", ex);
        }
    }

//    @Getter and Setter
    public List<Oferta> getOfertasFlitradas() {
        return ofertasFlitradas;
    }

    public void setOfertasFlitradas(List<Oferta> ofertasFlitradas) {
        this.ofertasFlitradas = ofertasFlitradas;
    }

    public Oferta getOfertaSeleccionada() {
        return ofertaSeleccionada;
    }

    public void setOfertaSeleccionada(Oferta ofertaSeleccionada) {
        this.ofertaSeleccionada = ofertaSeleccionada;
    }

}
