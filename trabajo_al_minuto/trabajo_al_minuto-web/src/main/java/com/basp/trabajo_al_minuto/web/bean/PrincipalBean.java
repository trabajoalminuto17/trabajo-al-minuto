/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.bean;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dte.OfertaAplicada;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.Citacion;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Persona;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.entity.Rol;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.ERROR_PAGE;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_RECHAZADA;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_RECHAZADA_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_RECHAZADA_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.USUARIO_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.USUARIO_OK;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class PrincipalBean extends ComponenteWeb implements Serializable {

    private Usuario usuarioSeleccionado;
    private Usuario usuarioSession;
    private Oferta ofertaSeleccionada;
    private UsuarioHasOferta usuarioHasOfertaSeleccionada;
    private Citacion citacionSeleccionada;
    private Prueba preubaSeleccionada;
    private List<Usuario> usuariosFlitrados;
    private List<Oferta> ofertasFlitradas;
    private List<UsuarioHasOferta> usuarioHasOfertaFlitradas;
    private List<Prueba> pruebasFlitradas;

    private Usuario newUsuario;
    private Persona newPersona;
    private Citacion newCitacion;

    private Boolean render;
    private Boolean citar;

    private StreamedContent streamedContent;

    public PrincipalBean() {
        newUsuario = new Usuario();
        newPersona = new Persona();
        newCitacion = new Citacion();
    }

    @PostConstruct
    public void init() {
        render = Boolean.FALSE;
        citar = Boolean.FALSE;
        usuarioSession = getUSER_LOGIN();
        if (usuarioSession != null) {
            try {
                if (usuarioSession.getRol().getRolId() == 1 || usuarioSession.getRol().getRolId() == 4) {
                    this.validateUsuarioSession();
                }
                this.validateOfertaSession();
                this.validateUsuarioHasOfertaSession();
                this.validatePruebaSession();
            } catch (BusinessException ex) {
                Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(ERROR_PAGE);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, "IOException.init", ex);
            }
        }

    }

    private void validateUsuarioSession() throws BusinessException {
        if (getUsuarioId() != null) {
            usuarioSeleccionado = usuarioEjb.findUsuario(getUsuarioId());
        }
    }

    private void validateOfertaSession() throws BusinessException {
        if (getOfertaId() != null) {
            ofertaSeleccionada = ofertaEjb.findOferta(getOfertaId());
        }
    }

    private void validateUsuarioHasOfertaSession() throws BusinessException {
        if (getUsuarioHasOfertaId() != null) {
            usuarioHasOfertaSeleccionada = usuarioEjb.findUsuarioHasOferta(getUsuarioHasOfertaId());
            citacionSeleccionada = usuarioHasOfertaSeleccionada.getCitacion();
        }
    }

    private void validatePruebaSession() throws BusinessException {
        if (getPruebaId() != null) {
            preubaSeleccionada = pruebaEjb.findPrueba(getPruebaId());
        }
    }

    public List<OfertaAplicada> getOfertasMasAplicadasByEmpresa() {
        try {
            return ofertaEjb.getOfertasMasAplicadasByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public List<Evaluacion> getUsuariosMejoresResultados() {
        try {
            return usuarioEjb.getUsuariosMejoresResultadosByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public List<Usuario> getUsuariosByEmpresa() {
        try {
            List<Usuario> r = usuarioEjb.getUsuariosByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
            r.remove(usuarioSession);
            return r;
        } catch (BusinessException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public List<Oferta> getOfertasByEmpreas() {
        try {
            return ofertaEjb.getOfertasByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public List<UsuarioHasOferta> getUsuarioHasOfertaByOferta() {
        try {
            return usuarioEjb.getUsuariosByOferta(ofertaSeleccionada.getOfertaId());
        } catch (BusinessException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public List<Prueba> getPruebasByPerfil() {
        try {
            return pruebaEjb.getPruebasByPerfil(ofertaSeleccionada.getPerfil().getPerfilId());
        } catch (BusinessException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public void onRowSelectVerUsuarios(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioId", ((Usuario) event.getObject()).getUsuarioId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_USUARIO_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, "onRowSelectViewChannels", ex);
        }
    }

    public void onRowSelectVerOfertas(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ofertaId", ((Oferta) event.getObject()).getOfertaId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_OFERTA_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, "onRowSelectVerOfertas", ex);
        }
    }

    public void onRowSelectVerUsuarioHasOfertas(SelectEvent event) {
        try {
            UsuarioHasOferta uho = (UsuarioHasOferta) event.getObject();
            if (uho.getEstado().getCatalogoId() != 9L) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioHasOfertaId", ((UsuarioHasOferta) event.getObject()).getUsuarioHasOfertaId());
                FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_POSTULACION_PAGE);
            } else {
                webMessage(CITACION_RECHAZADA);
            }
        } catch (IOException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, "onRowSelectVerUsuarioHasOfertas", ex);
        } finally {
            if (message != null) {
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void onRowSelectVerPruebas(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pruebaId", ((Prueba) event.getObject()).getPruebaId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_OFERTA_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, "onRowSelectVerPruebas", ex);
        }
    }

    public void updateUsuario() {
        try {
            usuarioSeleccionado = usuarioEjb.updateUsuario(usuarioSeleccionado);
            webMessage(CHANGE_OK);
        } catch (BusinessException ex) {
            webMessage(CHANGE_NOT);
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void createUsuario() {
        try {
            newUsuario.setCambioClave(Boolean.FALSE);
            newUsuario.setEmpresa(getUSER_LOGIN().getEmpresa());
            newUsuario.setEstado(Boolean.TRUE);
            newUsuario.setFechaCreacion(new Date());
            newUsuario.setPassword(newUsuario.getEmail());
            newUsuario.setRol(new Rol(2L));
            newUsuario.setTerminos(Boolean.FALSE);
            newUsuario.setPersona(newPersona);
            usuarioEjb.updateUsuario(newUsuario);
            render = Boolean.TRUE;
            webMessage(USUARIO_OK);
        } catch (BusinessException ex) {
            webMessage(USUARIO_NOT);
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void updateUsuarioHasOferta() {
        try {
            usuarioHasOfertaSeleccionada = usuarioEjb.updateUsuarioHasOferta(usuarioHasOfertaSeleccionada);
            webMessage(CHANGE_OK);
        } catch (BusinessException ex) {
            webMessage(CHANGE_NOT);
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void updateCitacion() {
        try {
            citacionSeleccionada = citacionEjb.updateCitacion(citacionSeleccionada);
            webMessage(CHANGE_OK);
        } catch (BusinessException ex) {
            webMessage(CHANGE_NOT);
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void createCitacion() {
        try {
            usuarioHasOfertaSeleccionada.setEstado(new Catalogo(11L));
            newCitacion.setActivarPruebas(Boolean.FALSE);
            newCitacion.setFechaCreacion(new Date());
            newCitacion.setResuelto(Boolean.FALSE);
            newCitacion.setEstado(Boolean.TRUE);
            newCitacion.setUsuarioAutor(usuarioSession);
            newCitacion.setUsuarioHasOferta(usuarioHasOfertaSeleccionada);
            citacionSeleccionada = citacionEjb.updateCitacion(newCitacion);
            usuarioHasOfertaSeleccionada = usuarioEjb.updateUsuarioHasOferta(usuarioHasOfertaSeleccionada);
            webMessage(CITACION_OK);
        } catch (BusinessException ex) {
            webMessage(CITACION_NOT);
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void rechazarCitacion() {
        try {
            usuarioHasOfertaSeleccionada.setEstado(new Catalogo(9L));
            usuarioHasOfertaSeleccionada = usuarioEjb.updateUsuarioHasOferta(usuarioHasOfertaSeleccionada);
            webMessage(CITACION_RECHAZADA_OK);
        } catch (BusinessException ex) {
            webMessage(CITACION_RECHAZADA_NOT);
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void descargarArchivoPdf(String url) {
        File file = new File(url);
        if (file.exists()) {
            InputStream stream = null;
            try {
                stream = new FileInputStream(file);
                streamedContent = new DefaultStreamedContent(stream, "pdf", file.getName());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void citarOk() {
        citar = Boolean.TRUE;
    }

    public void citarNot() {
        citar = Boolean.FALSE;
    }

//    GETTER AND SETTER
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Usuario> getUsuariosFlitrados() {
        return usuariosFlitrados;
    }

    public void setUsuariosFlitrados(List<Usuario> usuariosFlitrados) {
        this.usuariosFlitrados = usuariosFlitrados;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public Usuario getNewUsuario() {
        return newUsuario;
    }

    public void setNewUsuario(Usuario newUsuario) {
        this.newUsuario = newUsuario;
    }

    public Persona getNewPersona() {
        return newPersona;
    }

    public void setNewPersona(Persona newPersona) {
        this.newPersona = newPersona;
    }

    public Boolean getRender() {
        return render;
    }

    public void setRender(Boolean render) {
        this.render = render;
    }

    public Oferta getOfertaSeleccionada() {
        return ofertaSeleccionada;
    }

    public void setOfertaSeleccionada(Oferta ofertaSeleccionada) {
        this.ofertaSeleccionada = ofertaSeleccionada;
    }

    public List<Oferta> getOfertasFlitradas() {
        return ofertasFlitradas;
    }

    public void setOfertasFlitradas(List<Oferta> ofertasFlitradas) {
        this.ofertasFlitradas = ofertasFlitradas;
    }

    public UsuarioHasOferta getUsuarioHasOfertaSeleccionada() {
        return usuarioHasOfertaSeleccionada;
    }

    public void setUsuarioHasOfertaSeleccionada(UsuarioHasOferta usuarioHasOfertaSeleccionada) {
        this.usuarioHasOfertaSeleccionada = usuarioHasOfertaSeleccionada;
    }

    public List<UsuarioHasOferta> getUsuarioHasOfertaFlitradas() {
        return usuarioHasOfertaFlitradas;
    }

    public void setUsuarioHasOfertaFlitradas(List<UsuarioHasOferta> usuarioHasOfertaFlitradas) {
        this.usuarioHasOfertaFlitradas = usuarioHasOfertaFlitradas;
    }

    public Boolean getCitar() {
        return citar;
    }

    public void setCitar(Boolean citar) {
        this.citar = citar;
    }

    public Citacion getNewCitacion() {
        return newCitacion;
    }

    public void setNewCitacion(Citacion newCitacion) {
        this.newCitacion = newCitacion;
    }

    public Citacion getCitacionSeleccionada() {
        return citacionSeleccionada;
    }

    public void setCitacionSeleccionada(Citacion citacionSeleccionada) {
        this.citacionSeleccionada = citacionSeleccionada;
    }

    public Prueba getPreubaSeleccionada() {
        return preubaSeleccionada;
    }

    public void setPreubaSeleccionada(Prueba preubaSeleccionada) {
        this.preubaSeleccionada = preubaSeleccionada;
    }

    public List<Prueba> getPruebasFlitradas() {
        return pruebasFlitradas;
    }

    public void setPruebasFlitradas(List<Prueba> pruebasFlitradas) {
        this.pruebasFlitradas = pruebasFlitradas;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

}
