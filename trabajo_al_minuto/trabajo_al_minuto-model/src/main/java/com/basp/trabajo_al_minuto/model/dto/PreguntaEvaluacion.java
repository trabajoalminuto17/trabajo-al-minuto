/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author BASP
 */
public class PreguntaEvaluacion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String enunciado;
    private List<OpcionEvaluacion> opciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<OpcionEvaluacion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionEvaluacion> opciones) {
        this.opciones = opciones;
    }

}
