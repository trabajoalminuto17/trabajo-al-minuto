/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.business;

import com.basp.trabajo_al_minuto.model.dto.ErrorMessage;
import java.io.Serializable;

/**
 *
 * @author BASP
 */
public class BusinessException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CAUGHT = "Business Exception Caught!\n";
    private Class clazz;
    private String message;
    private StackTraceElement[] stackTraceElement;
    private String errorMethod;
    private Integer errorLine;
    private ErrorMessage errorMessage;

    public BusinessException() {
    }

    /**
     * Captura y muestra las excepciones de negocio
     */
    public BusinessException(Exception e) {
        this.clazz = e.getClass();
        this.message = e.getMessage();
        this.stackTraceElement = e.getStackTrace();
        finalTrace(stackTraceElement);
        errorMessage = new ErrorMessage(clazz.getSimpleName(), errorMethod, errorLine, message);
    }

    /**
     * Captura y muestra las excepciones del desarrollador
     */
    public String developerException() {
        return CAUGHT + " --> Exception = (" + clazz + ")\n "
                + "--> Trace = (" + getTraceFor(stackTraceElement) + ")\n "
                + "--> Message = (" + message + ")";
    }

    /**
     * Inidica la clase el metodo y la linea donde ocurrio la excepcion
     */
    private String getTraceFor(StackTraceElement[] elements) {
        StringBuilder trace = new StringBuilder();
        for (StackTraceElement element : elements) {
            if (!element.isNativeMethod()) {
                if (element.getMethodName().equals("invoke")) {
                    break;
                }
                trace.append("Class : ").append(element.getClassName());
                trace.append(", Method : ").append(element.getMethodName());
                trace.append(", Line : ").append(element.getLineNumber()).append("\n");
            }

        }
        return trace.toString();
    }

    private void finalTrace(StackTraceElement[] elements) {
        for (StackTraceElement element : elements) {
            if (!element.isNativeMethod()) {
                if (element.getMethodName().equals("invoke")) {
                    break;
                }
                this.errorMethod = element.getMethodName();
                this.errorLine = element.getLineNumber();
            }

        }
    }

    /**
     *Metodos get y set *
     */
    public Class getClazz() {
        return clazz;
    }

    public String gerErrorMethod() {
        return errorMethod;
    }

    public Integer getErrorLine() {
        return errorLine;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
