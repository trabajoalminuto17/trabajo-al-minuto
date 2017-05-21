/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_PRUEBA_PLANTILLA_PAGE;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.PruebaPlantilla;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VerPruebaView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PruebaPlantilla> filteredPruebas;
    private PruebaPlantilla pruebaSeleccionada;

    public List<PruebaPlantilla> getPruebasByEmpresa() {
        try {
            Usuario u = getUserLogin();
            return pruebaEjb.getPruebasPlantillaByEmpresa(u.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(VerPruebaView.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public void onRowSelectVerPruebas(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pruebaPlantillaId", ((PruebaPlantilla) event.getObject()).getPruebaId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_PRUEBA_PLANTILLA_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(VerPruebaView.class.getName()).log(Level.SEVERE, "onRowSelectVerOfertas", ex);
        }
    }

//    @Getter and Setter
    public List<PruebaPlantilla> getFilteredPruebas() {
        return filteredPruebas;
    }

    public void setFilteredPruebas(List<PruebaPlantilla> filteredPruebas) {
        this.filteredPruebas = filteredPruebas;
    }

    public PruebaPlantilla getPruebaSeleccionada() {
        return pruebaSeleccionada;
    }

    public void setPruebaSeleccionada(PruebaPlantilla pruebaSeleccionada) {
        this.pruebaSeleccionada = pruebaSeleccionada;
    }

}
