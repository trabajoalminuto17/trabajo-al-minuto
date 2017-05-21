/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dte.OfertaAplicada;
import com.basp.trabajo_al_minuto.service.entity.Citacion;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_OFERTA_PAGE;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.formatDate;
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

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class InicioView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuarioSession;
    private List<OfertaAplicada> ofertasPopulares;
    private List<Evaluacion> usuariosMejoresResultados;
    private List<Citacion> proximasCitaciones;

    @PostConstruct
    public void init() {
        try {
            usuarioSession = getUserLogin();
            if (usuarioSession.getRol().getRolId() == 3L) {
                ofertasPopulares = ofertaEjb.getOfertasMasAplicadas();
                List<Citacion> response = citacionEjb.getCitacionesActivasByUsuario(usuarioSession.getUsuarioId());
                if (response.size() <= 6) {
                    proximasCitaciones = response;
                } else {
                    proximasCitaciones = response.subList(0, 5);
                }
            } else {
                ofertasPopulares = ofertaEjb.getOfertasMasAplicadasByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
                usuariosMejoresResultados = usuarioEjb.getUsuariosMejoresResultadosByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
            }
        } catch (BusinessException ex) {
            Logger.getLogger(InicioView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
    }

    public void goDetalleOferta(Oferta o) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ofertaId", o.getOfertaId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_OFERTA_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(InicioView.class.getName()).log(Level.SEVERE, "onRowSelectVerOfertas", ex);
        }
    }

    public String getFormatDate(Date date) {
        return formatDate(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

//    @Getter and Setter
    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public List<OfertaAplicada> getOfertasPopulares() {
        return ofertasPopulares;
    }

    public void setOfertasPopulares(List<OfertaAplicada> ofertasPopulares) {
        this.ofertasPopulares = ofertasPopulares;
    }

    public List<Evaluacion> getUsuariosMejoresResultados() {
        return usuariosMejoresResultados;
    }

    public void setUsuariosMejoresResultados(List<Evaluacion> usuariosMejoresResultados) {
        this.usuariosMejoresResultados = usuariosMejoresResultados;
    }

    public List<Citacion> getProximasCitaciones() {
        return proximasCitaciones;
    }

    public void setProximasCitaciones(List<Citacion> proximasCitaciones) {
        this.proximasCitaciones = proximasCitaciones;
    }

}
