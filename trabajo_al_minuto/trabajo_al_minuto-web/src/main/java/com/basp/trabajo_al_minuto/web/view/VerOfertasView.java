/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_OFERTA_PAGE;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
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
public class VerOfertasView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Oferta> ofertasFlitradas;
    private Oferta ofertaSeleccionada;
    private String perfil;
    private Long area;
    private Integer cantidadOfertas;
    private List<Oferta> ofertasActualizadas;
    private Usuario usuariologin;

    @PostConstruct
    public void init() {
        try {
            usuariologin = getUserLogin();
            if (usuariologin.getRol().getRolId() == 3) {
                ofertasActualizadas = ofertaEjb.getOfertasActivas();
                cantidadOfertas = ofertasActualizadas.size();
            }
        } catch (BusinessException ex) {
            Logger.getLogger(VerOfertasView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Oferta> getOfertasByEmpreas() {
        try {
            return ofertaEjb.getOfertasByEmpresa(usuariologin.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(VerOfertasView.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
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
            Logger.getLogger(VerOfertasView.class.getName()).log(Level.SEVERE, ex.developerException());
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
            Logger.getLogger(VerOfertasView.class.getName()).log(Level.SEVERE, ex.developerException());
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

    public Usuario getUsuariologin() {
        return usuariologin;
    }

    public void setUsuariologin(Usuario usuariologin) {
        this.usuariologin = usuariologin;
    }

    public List<Oferta> getOfertasActualizadas() {
        return ofertasActualizadas;
    }

    public void setOfertasActualizadas(List<Oferta> ofertasActualizadas) {
        this.ofertasActualizadas = ofertasActualizadas;
    }

}
