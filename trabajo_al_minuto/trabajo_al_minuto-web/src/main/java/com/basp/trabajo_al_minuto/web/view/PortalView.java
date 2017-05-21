/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.VER_OFERTA_EXTERNAL;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.propiedadesItem;
import java.io.IOException;
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
public class PortalView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private String perfil;
    private Long area;
    private Integer cantidadOfertas;

    @PostConstruct
    public void init() {
        try {
            cantidadOfertas = ofertaEjb.getOfertasActivas().size();
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

    public void filteredOferta() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("perfilFiltro", perfil);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("areaFiltro", area);
            FacesContext.getCurrentInstance().getExternalContext().redirect(VER_OFERTA_EXTERNAL);
        } catch (IOException ex) {
            Logger.getLogger(PortalView.class.getName()).log(Level.SEVERE, "filteredOferta", ex);
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

}
