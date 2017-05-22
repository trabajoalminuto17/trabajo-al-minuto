/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Perfil;
import com.basp.trabajo_al_minuto.service.entity.PerfilHasPrueba;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.entity.PruebaPlantilla;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
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
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class CrearOfertaView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Oferta newOferta;
    private Perfil newPerfil;
    private Long area;
    private PruebaPlantilla pruebaPlantillaSeleccionada;
    private PerfilHasPrueba pruebaSeleccionada;
    private List<PruebaPlantilla> pruebasSource;
    private List<PerfilHasPrueba> pruebasTarget;

    public CrearOfertaView() {
        newOferta = new Oferta();
        newPerfil = new Perfil();
    }

    @PostConstruct
    public void init() {
        try {
            Usuario u = getUserLogin();
            pruebasTarget = new ArrayList();
            pruebasSource = pruebaEjb.getPruebasPlantillaByEmpresa(u.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(DetallePruebaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createOferta() {
        try {
            newOferta.setPerfil(newPerfil);
            newOferta.setUsuarioAutor(getUserLogin());
            newOferta.setFechaExpiracion(newOferta.getFechaExpiracion());
            newOferta.setEstado(Boolean.TRUE);
            newPerfil.setTitulo(newPerfil.getTitulo());
            newPerfil.setDescripcion(newPerfil.getDescripcion());
            newPerfil.setSalario(newPerfil.getSalario());
            newPerfil.setRequerimientos(newPerfil.getRequerimientos());
            newPerfil.setArea(new Catalogo(area));
            newPerfil.setPerfilHasPruebaList(pruebasTarget);
            newOferta.setPerfil(newPerfil);

            ofertaEjb.updateOferta(newOferta);
        } catch (BusinessException ex) {
            Logger.getLogger(CrearOfertaView.class.getName()).log(Level.SEVERE, ex.developerException());
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
            Logger.getLogger(DetallePruebaView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
        return response;
    }

    public void onRowSelectPruebasPlantilla(SelectEvent event) {
        PruebaPlantilla pp = (PruebaPlantilla) event.getObject();
        PerfilHasPrueba php = new PerfilHasPrueba();
        Prueba p = new Prueba();
        p.setArea(pp.getArea());
        p.setDescripcion(pp.getDescripcion());
        p.setEstado(Boolean.TRUE);
        p.setLimiteTiempo(pp.getLimiteTiempo());
        p.setNombre(pp.getNombre());
        p.setPorcentajeMinimo(pp.getPorcentajeMinimo());
        php.setPerfil(newPerfil);
        php.setPrueba(p);
        pruebasTarget.add(php);

    }

    public Oferta getNewOferta() {
        return newOferta;
    }

    public void setNewOferta(Oferta newOferta) {
        this.newOferta = newOferta;
    }

    public Perfil getNewPerfil() {
        return newPerfil;
    }

    public void setNewPerfil(Perfil newPerfil) {
        this.newPerfil = newPerfil;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public List<PruebaPlantilla> getPruebasSource() {
        return pruebasSource;
    }

    public void setPruebasSource(List<PruebaPlantilla> pruebasSource) {
        this.pruebasSource = pruebasSource;
    }

    public List<PerfilHasPrueba> getPruebasTarget() {
        return pruebasTarget;
    }

    public void setPruebasTarget(List<PerfilHasPrueba> pruebasTarget) {
        this.pruebasTarget = pruebasTarget;
    }

    public PruebaPlantilla getPruebaPlantillaSeleccionada() {
        return pruebaPlantillaSeleccionada;
    }

    public void setPruebaPlantillaSeleccionada(PruebaPlantilla pruebaPlantillaSeleccionada) {
        this.pruebaPlantillaSeleccionada = pruebaPlantillaSeleccionada;
    }

    public PerfilHasPrueba getPruebaSeleccionada() {
        return pruebaSeleccionada;
    }

    public void setPruebaSeleccionada(PerfilHasPrueba pruebaSeleccionada) {
        this.pruebaSeleccionada = pruebaSeleccionada;
    }

}
