/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.model.dto.OpcionEvaluacion;
import com.basp.trabajo_al_minuto.model.dto.PreguntaEvaluacion;
import com.basp.trabajo_al_minuto.model.dto.PruebaEvaluacion;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Opcion;
import com.basp.trabajo_al_minuto.service.entity.Pregunta;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.entity.Respuesta;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.QUESTION_NULL;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.QUESTION_REPEAT;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
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
public class EvaluacionView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private PruebaEvaluacion pruebaEvaluacion;
    private Evaluacion evaluacion;

    @PostConstruct
    public void init() {
        try {
            if (getEvaluacionId() != null && getPruebaId() != null) {
                pruebaEvaluacion = new PruebaEvaluacion();
                evaluacion = pruebaEjb.findEvaluacion(getEvaluacionId());
                Prueba p = pruebaEjb.findPrueba(getPruebaId());
                pruebaEvaluacion = setPrueba(p);
            }
        } catch (BusinessException ex) {
            Logger.getLogger(EvaluacionView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
    }

    public void finalizarPrueba() {
        try {
            Boolean b;
            for (PreguntaEvaluacion pe : pruebaEvaluacion.getPreguntas()) {
                b = validarPreguntaNull(pe);
                if (!b) {
                    b = validarPreguntaRepeat(pe);
                    if (!b) {
                        for (OpcionEvaluacion oe : pe.getOpciones()) {
                            Respuesta r = new Respuesta();
                            r.setEvaluacion(evaluacion);
                            r.setOpcion(new Opcion(oe.getId()));
                        }
                    } else {
                        message = webMessage(QUESTION_REPEAT);
                    }
                } else {
                    message = webMessage(QUESTION_NULL);
                }
            }
        } finally {
            if (message != null) {
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }

    }

    public void onTimeout() {
        try {
            for (PreguntaEvaluacion pe : pruebaEvaluacion.getPreguntas()) {
                Boolean a = validarPreguntaNull(pe);
                Boolean b = validarPreguntaRepeat(pe);
                if (a || b) {
                    
                } else {
                    for (OpcionEvaluacion oe : pe.getOpciones()) {
                        if (oe.getRespuesta()) {
                            Respuesta r = new Respuesta();
                            r.setEvaluacion(evaluacion);
                            r.setOpcion(new Opcion(oe.getId()));
                            break;
                        }
                    }
                }
            }
        } finally {
            if (message != null) {
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    private PruebaEvaluacion setPrueba(Prueba p) {
        PruebaEvaluacion pe = new PruebaEvaluacion();
        pe.setArea(p.getArea().getValor());
        pe.setAreaId(p.getArea().getCatalogoId());
        pe.setDescripcion(p.getDescripcion());
        pe.setNombre(p.getNombre());
        pe.setId(p.getPruebaId());
        pe.setPorcentaje(p.getPorcentajeMinimo());
        pe.setTiempo(getFormatDate(p.getLimiteTiempo()));
        pe.setPreguntas(setPreguntas(p));
        return pe;
    }

    private List<PreguntaEvaluacion> setPreguntas(Prueba p) {
        List<PreguntaEvaluacion> preguntas = new ArrayList();
        for (Pregunta pregunta : p.getPreguntaList()) {
            PreguntaEvaluacion pe = new PreguntaEvaluacion();
            pe.setEnunciado(pregunta.getEnunciado());
            pe.setId(pe.getId());
            pe.setOpciones(setOpciones(pregunta));
            preguntas.add(pe);
        }
        return preguntas;
    }

    private List<OpcionEvaluacion> setOpciones(Pregunta p) {
        List<OpcionEvaluacion> opciones = new ArrayList();
        for (Opcion o : p.getOpcionList()) {
            OpcionEvaluacion oe = new OpcionEvaluacion();
            oe.setEnunciado(o.getEnunciado());
            oe.setCorrecta(o.getCorrecta());
            oe.setId(o.getOpcionId());
            oe.setRespuesta(Boolean.FALSE);
            opciones.add(oe);
        }
        return opciones;
    }

    public void cambiarOpcion(PreguntaEvaluacion pregunta, OpcionEvaluacion opcion) {
        Boolean b = Boolean.FALSE;
        if (opcion.getRespuesta()) {
            for (OpcionEvaluacion op : pregunta.getOpciones()) {
                if (!op.equals(opcion)) {
                    op.setRespuesta(Boolean.FALSE);
                }
            }
        } else {
            for (OpcionEvaluacion op : pregunta.getOpciones()) {
                if (b) {
                    op.setRespuesta(Boolean.FALSE);
                }
                if (op.getRespuesta()) {
                    b = Boolean.TRUE;
                }
            }
        }
    }

    private Boolean validarPreguntaNull(PreguntaEvaluacion pe) {
        Boolean b = Boolean.FALSE;
        for (OpcionEvaluacion oe : pe.getOpciones()) {
            b = oe.getRespuesta();
            if (b) {
                break;
            }
        }
        return b;

    }

    private Boolean validarPreguntaRepeat(PreguntaEvaluacion pe) {
        Integer cont = 0;
        for (OpcionEvaluacion oe : pe.getOpciones()) {
            if (oe.getRespuesta()) {
                cont++;
            }
            if (cont > 1) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;

    }

    public Long getFormatDate(Date date) {
        if (date != null) {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toEpochSecond(ZoneOffset.UTC);
        }
        return 0L;
    }

//    @Getter and Setter
    public PruebaEvaluacion getPruebaEvaluacion() {
        return pruebaEvaluacion;
    }

    public void setPruebaEvaluacion(PruebaEvaluacion pruebaEvaluacion) {
        this.pruebaEvaluacion = pruebaEvaluacion;
    }

}
