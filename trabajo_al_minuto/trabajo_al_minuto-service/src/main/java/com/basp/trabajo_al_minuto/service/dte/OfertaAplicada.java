/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.dte;

import com.basp.trabajo_al_minuto.service.entity.Oferta;

/**
 *
 * @author BASP
 */
public class OfertaAplicada {

    private Oferta oferta;
    private Long cantidadPostulados;

    public OfertaAplicada(Oferta oferta, Long cantidadPostulados) {
        this.oferta = oferta;
        this.cantidadPostulados = cantidadPostulados;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public Long getCantidadPostulados() {
        return cantidadPostulados;
    }

    public void setCantidadPostulados(Long cantidadPostulados) {
        this.cantidadPostulados = cantidadPostulados;
    }

}
