/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_PRUEBA_PLANTILLA_PAGE;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.Citacion;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.entity.PruebaPlantilla;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_EVALUACION_PAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.PORTAL_PAGE;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.formatDateTime;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
public class VerPruebaView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PruebaPlantilla> filteredPruebasPlantilla;
    private List<Prueba> filteredPruebas;
    private PruebaPlantilla pruebaPlantillaSeleccionada;
    private Prueba pruebaSeleccionada;
    private List<Prueba> pruebasByPerfil;
    private List<PruebaPlantilla> pruebasByEmpresa;
    private Usuario usuariologin;
    private Boolean pruebasCompletas;

    @PostConstruct
    public void init() {
        try {
            usuariologin = getUserLogin();
            pruebasCompletas = Boolean.FALSE;
            if (usuariologin.getRol().getRolId() == 3L) {
                if (getOfertaId() != null) {
                    Oferta o = ofertaEjb.findOferta(getOfertaId());
                    pruebasByPerfil = pruebaEjb.getPruebasByPerfil(o.getPerfil().getPerfilId());
                    if (getPreubasResueltas() != null) {
                        for (Prueba p : getPreubasResueltas()) {
                            pruebasByPerfil.remove(p);
                        }
                    }
                    if (pruebasByPerfil.isEmpty()) {
                        usuariologin.getCandidato().setPruebasActivas(Boolean.FALSE);
                        usuariologin = usuarioEjb.updateUsuario(usuariologin);
                        pruebasCompletas = Boolean.TRUE;
                        Citacion c = citacionEjb.findCitacion(getCitacionId());
                        c.setActivarPruebas(Boolean.FALSE);
                        c.setResuelto(Boolean.TRUE);
                        c.getUsuarioHasOferta().setEstado(new Catalogo(12L));
                        citacionEjb.updateCitacion(c);
                    }
                }
            } else {
                pruebasByEmpresa = pruebaEjb.getPruebasPlantillaByEmpresa(usuariologin.getEmpresa().getEmpresaId());
            }
        } catch (BusinessException ex) {
            Logger.getLogger(EvaluacionView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
    }

    public void onRowSelectVerPruebas(SelectEvent event) {
        try {
            if (usuariologin.getRol().getRolId() == 3L) {
                pruebaSeleccionada = (Prueba) event.getObject();
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pruebaPlantillaId", ((PruebaPlantilla) event.getObject()).getPruebaId());
                FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_PRUEBA_PLANTILLA_PAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(VerPruebaView.class.getName()).log(Level.SEVERE, "onRowSelectVerOfertas", ex);
        }
    }

    public void comenzarPrueba() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pruebaId", pruebaSeleccionada.getPruebaId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_EVALUACION_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(VerPruebaView.class.getName()).log(Level.SEVERE, "comenzarPrueba", ex);
        }
    }

    public void finalizarPrueba() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect(PORTAL_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(VerPruebaView.class.getName()).log(Level.SEVERE, "finalizarPrueba", ex);
        }
    }

    public String getFormatDate(Date date) {
        if (date != null) {
            return formatDateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
        }
        return "";
    }

//    @Getter and Setter
    public List<PruebaPlantilla> getFilteredPruebasPlantilla() {
        return filteredPruebasPlantilla;
    }

    public void setFilteredPruebasPlantilla(List<PruebaPlantilla> filteredPruebasPlantilla) {
        this.filteredPruebasPlantilla = filteredPruebasPlantilla;
    }

    public List<Prueba> getFilteredPruebas() {
        return filteredPruebas;
    }

    public void setFilteredPruebas(List<Prueba> filteredPruebas) {
        this.filteredPruebas = filteredPruebas;
    }

    public PruebaPlantilla getPruebaPlantillaSeleccionada() {
        return pruebaPlantillaSeleccionada;
    }

    public void setPruebaPlantillaSeleccionada(PruebaPlantilla pruebaPlantillaSeleccionada) {
        this.pruebaPlantillaSeleccionada = pruebaPlantillaSeleccionada;
    }

    public Prueba getPruebaSeleccionada() {
        return pruebaSeleccionada;
    }

    public void setPruebaSeleccionada(Prueba pruebaSeleccionada) {
        this.pruebaSeleccionada = pruebaSeleccionada;
    }

    public List<Prueba> getPruebasByPerfil() {
        return pruebasByPerfil;
    }

    public void setPruebasByPerfil(List<Prueba> pruebasByPerfil) {
        this.pruebasByPerfil = pruebasByPerfil;
    }

    public List<PruebaPlantilla> getPruebasByEmpresa() {
        return pruebasByEmpresa;
    }

    public void setPruebasByEmpresa(List<PruebaPlantilla> pruebasByEmpresa) {
        this.pruebasByEmpresa = pruebasByEmpresa;
    }

    public Usuario getUsuariologin() {
        return usuariologin;
    }

    public void setUsuariologin(Usuario usuariologin) {
        this.usuariologin = usuariologin;
    }

    public Boolean getPruebasCompletas() {
        return pruebasCompletas;
    }

    public void setPruebasCompletas(Boolean pruebasCompletas) {
        this.pruebasCompletas = pruebasCompletas;
    }

}
