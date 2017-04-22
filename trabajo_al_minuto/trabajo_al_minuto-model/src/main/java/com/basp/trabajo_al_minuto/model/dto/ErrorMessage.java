/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.dto;

/**
 *
 * @author BASP
 */
public class ErrorMessage {

    private String clazz;
    private String method;
    private Integer line;
    private String message;

    public ErrorMessage(String clazz, String method, Integer line, String message) {
        this.clazz = clazz;
        this.method = method;
        this.line = line;
        this.message = message;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
