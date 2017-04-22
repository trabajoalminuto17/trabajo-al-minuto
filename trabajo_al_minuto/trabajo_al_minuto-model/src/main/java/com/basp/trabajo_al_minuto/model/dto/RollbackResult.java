/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.dto;

/**
 *
 * @author Bryan.Silva
 */
public class RollbackResult {

    private String className;
    private String cause;
    private String invalidValue;

    public RollbackResult(String className, String cause, String invalidValue) {
        this.className = className;
        this.cause = cause;
        this.invalidValue = invalidValue;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(String invalidValue) {
        this.invalidValue = invalidValue;
    }

}
