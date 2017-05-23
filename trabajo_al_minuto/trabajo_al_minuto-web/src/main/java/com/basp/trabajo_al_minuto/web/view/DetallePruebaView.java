/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.OpcionPlantilla;
import com.basp.trabajo_al_minuto.service.entity.PreguntaPlantilla;
import com.basp.trabajo_al_minuto.service.entity.PruebaPlantilla;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_OK;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.propiedadesItem;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
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
    private Long area;

    /**
     * Codigo a ejecutar una vez se ha instanciado la clase
     */
    @PostConstruct
    public void init() {
        try {
            if (getPruebaPlantillaId() != null) {
                pruebaSeleccionada = pruebaEjb.findPruebaPlantilla(getPruebaPlantillaId());
                area = pruebaSeleccionada.getArea().getCatalogoId();
            }
        } catch (BusinessException ex) {
            Logger.getLogger(DetallePruebaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Genera la lista que será mostrada al usuario con las áreas a las cuales
     * se pueden asociar una oferta
     */
    public List<SelectItem> getAreas() {
        List<SelectItem> response = new ArrayList();
        response.add(propiedadesItem(new SelectItem(-1, "Seleccione area..")));
        try {
            List<Catalogo> list = getCatalogosByParent(1L);
            for (Catalogo c : list) {
                response.add(new SelectItem(c.getCatalogoId(), c.getValor()));
            }
        } catch (BusinessException ex) {
            Logger.getLogger(DetallePruebaView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
        return response;
    }

    /**
     **
     */
    public void cambiarOpcion(PreguntaPlantilla pregunta, OpcionPlantilla opcion) {
        Boolean b = Boolean.FALSE;
        if (opcion.getCorrecta()) {
            for (OpcionPlantilla op : pregunta.getOpcionPlantillaList()) {
                if (!op.equals(opcion)) {
                    op.setCorrecta(Boolean.FALSE);
                }
            }
        } else {
            for (OpcionPlantilla op : pregunta.getOpcionPlantillaList()) {
                if (b) {
                    op.setCorrecta(Boolean.FALSE);
                }
                if (op.getCorrecta()) {
                    b = Boolean.TRUE;
                }
            }
        }
    }

    public void addPregunta() {
        PreguntaPlantilla pp = new PreguntaPlantilla();
        List<OpcionPlantilla> opciones = new ArrayList();
        opciones.add(addOpcion());
        opciones.add(addOpcion());
        pp.setEnunciado("Ingrese enunciado");
        pp.setPruebaPlantillaPruebaId(pruebaSeleccionada);
        pp.setOpcionPlantillaList(opciones);
        pruebaSeleccionada.getPreguntaPlantillaList().add(pp);
    }

    public OpcionPlantilla addOpcion() {
        OpcionPlantilla op = new OpcionPlantilla();
        op.setEnunciado("Ingrese enunciado");
        op.setCorrecta(Boolean.FALSE);
        return op;
    }

    public void addOpcion(PreguntaPlantilla pregunta) {
        OpcionPlantilla op = new OpcionPlantilla();
        op.setEnunciado("Ingrese enunciado");
        op.setCorrecta(Boolean.FALSE);
        op.setPreguntaPlantillaPreguntaId(pregunta);
        pregunta.getOpcionPlantillaList().add(op);
    }

    public void removePregunta(PreguntaPlantilla pregunta) {
        pruebaSeleccionada.getPreguntaPlantillaList().remove(pregunta);
    }

    public void removeOpcion(PreguntaPlantilla pregunta, OpcionPlantilla opcion) {
        for (PreguntaPlantilla pp : pruebaSeleccionada.getPreguntaPlantillaList()) {
            if (pp.equals(pregunta)) {
                pp.getOpcionPlantillaList().remove(opcion);
            }
        }
    }

    public void updatePruebaPlantilla() {
        try {
            pruebaSeleccionada = pruebaEjb.updatePruebaPlantilla(pruebaSeleccionada);
            message = webMessage(CHANGE_OK);
        } catch (BusinessException ex) {
            message = webMessage(CHANGE_NOT);
            Logger.getLogger(DetallePruebaView.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

//    @Getter and Setter
    public PruebaPlantilla getPruebaSeleccionada() {
        return pruebaSeleccionada;
    }

    public void setPruebaSeleccionada(PruebaPlantilla pruebaSeleccionada) {
        this.pruebaSeleccionada = pruebaSeleccionada;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }
}
