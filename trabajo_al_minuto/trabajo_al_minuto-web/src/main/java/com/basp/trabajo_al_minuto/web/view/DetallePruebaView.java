/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.PruebaPlantilla;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.propiedadesItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class DetallePruebaView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private PruebaPlantilla pruebaSeleccionada;

    @PostConstruct
    public void init() {
        try {
            if (getPruebaPlantillaId() != null) {
                pruebaSeleccionada = pruebaEjb.findPruebaPlantilla(getPruebaPlantillaId());
            }
        } catch (BusinessException ex) {
            Logger.getLogger(DetallePruebaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SelectItem> getAreas() {
        List<SelectItem> response = new ArrayList();
        response.add(propiedadesItem(new SelectItem(-1, "Seleccione..")));
        try {
            List<Catalogo> list = getCatalogosByParent(5L);
            for (Catalogo c : list) {
                response.add(new SelectItem(c.getCatalogoId(), c.getValor()));
            }
        } catch (BusinessException ex) {
            Logger.getLogger(DetallePruebaView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
        return response;
    }

//    @Getter and Setter
    public PruebaPlantilla getPruebaSeleccionada() {
        return pruebaSeleccionada;
    }

    public void setPruebaSeleccionada(PruebaPlantilla pruebaSeleccionada) {
        this.pruebaSeleccionada = pruebaSeleccionada;
    }
}
