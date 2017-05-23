/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class DetalleOfertaExternalView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Oferta ofertaSeleccionada;

    /**
     * Codigo a ejecutar una vez se ha instanciado la clase
     */
    @PostConstruct
    public void init() {
        try {
            if (getOfertaExternalId() != null) {
                ofertaSeleccionada = ofertaEjb.findOferta(getOfertaExternalId());
            }
        } catch (BusinessException ex) {
            Logger.getLogger(DetalleOfertaExternalView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Getter and Setter
    public Oferta getOfertaSeleccionada() {
        return ofertaSeleccionada;
    }

    public void setOfertaSeleccionada(Oferta ofertaSeleccionada) {
        this.ofertaSeleccionada = ofertaSeleccionada;
    }
}
