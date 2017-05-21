/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_OFERTA_EXTERNAL_PAGE;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.propiedadesItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class PortalView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private String perfil;
    private Long area;
    private Integer cantidadOfertas;
    private List<Oferta> ofertasFlitradas;
    private List<Oferta> ofertasActualizadas;
    private Oferta ofertaSeleccionada;

    @PostConstruct
    public void init() {
        try {
            ofertasActualizadas = ofertaEjb.getOfertasActivas();
            cantidadOfertas = ofertasActualizadas.size();
        } catch (BusinessException ex) {
            Logger.getLogger(PortalView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
    }

    public List<SelectItem> getAreas() {
        List<SelectItem> response = new ArrayList();
        response.add(propiedadesItem(new SelectItem(-1, "Seleccione area..")));
        try {
            List<Catalogo> list = getCatalogosByParent(1L);
            for (Catalogo c : list) {
                response.add(new SelectItem(c.getCatalogoId(), c.getValor()));
            }
        } catch (BusinessException ex) {
            Logger.getLogger(PortalView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
        return response;
    }

    public void actualizarOfertas() {
        try {
            List<String> palabras = new ArrayList();
            if (perfil != null && !("").equals(perfil)) {
                palabras = Arrays.asList(perfil.toLowerCase().split(","));
            }
            ofertasActualizadas = ofertaEjb.getOfertasExternal(area, palabras);
        } catch (BusinessException ex) {
            Logger.getLogger(PortalView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
    }

    public void onRowSelectVerExternalOfertas(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ofertaexternalId", ((Oferta) event.getObject()).getOfertaId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_OFERTA_EXTERNAL_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(PortalView.class.getName()).log(Level.SEVERE, "onRowSelectVerExternalOfertas", ex);
        }
    }

//    @Getter and Setter
    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Integer getCantidadOfertas() {
        return cantidadOfertas;
    }

    public void setCantidadOfertas(Integer cantidadOfertas) {
        this.cantidadOfertas = cantidadOfertas;
    }

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

    public List<Oferta> getOfertasActualizadas() {
        return ofertasActualizadas;
    }

    public void setOfertasActualizadas(List<Oferta> ofertasActualizadas) {
        this.ofertasActualizadas = ofertasActualizadas;
    }

}
