/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bryan Silva
 */
@XmlRootElement
public class EmailMessage {

    private String user;
    private String password;
    private String from;
    private String mask;
    private String host;
    private Integer port;
    private String subject;
    private String bodyMessage;
    private String mimeTypeMessage;
    private Boolean starttls;
    private List<String> toList;
    private List<String> ccList;
    private List<String> ccoList;
    private List<String> filePaths;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(String bodyMessage) {
        this.bodyMessage = bodyMessage;
    }

    public List<String> getToList() {
        return toList;
    }

    public void setToList(List<String> toList) {
        this.toList = toList;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public void setCcList(List<String> ccList) {
        this.ccList = ccList;
    }

    public List<String> getCcoList() {
        return ccoList;
    }

    public void setCcoList(List<String> ccoList) {
        this.ccoList = ccoList;
    }

    public List<String> getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getMimeTypeMessage() {
        return mimeTypeMessage;
    }

    public void setMimeTypeMessage(String mimeTypeMessage) {
        this.mimeTypeMessage = mimeTypeMessage;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getStarttls() {
        return starttls;
    }

    public void setStarttls(Boolean starttls) {
        this.starttls = starttls;
    }
    
}
