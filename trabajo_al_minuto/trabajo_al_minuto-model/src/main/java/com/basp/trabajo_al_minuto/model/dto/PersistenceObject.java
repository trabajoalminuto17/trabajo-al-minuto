/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.dto;

import java.io.Serializable;

/**
 *
 * @author BASP
 */
public class PersistenceObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Class resultClass;
    private String sqlResultSetMapping;
    private String querySql;
    private Integer queryType;
    private Object arg;
    private Object[] args;
    private Integer limitResults;

    public PersistenceObject(String querySql, Integer queryType) {
        this.querySql = querySql;
        this.queryType = queryType;
    }

    public PersistenceObject(String querySql, Integer queryType, Object arg) {
        this.querySql = querySql;
        this.queryType = queryType;
        this.arg = arg;
    }

    public PersistenceObject(String querySql, Integer queryType, Object[] args) {
        this.querySql = querySql;
        this.queryType = queryType;
        this.args = args;
    }

    public PersistenceObject(Class resultClass, String querySql, Integer queryType) {
        this.resultClass = resultClass;
        this.querySql = querySql;
        this.queryType = queryType;
    }

    public PersistenceObject(Class resultClass, String querySql, Integer queryType, Object arg) {
        this.resultClass = resultClass;
        this.querySql = querySql;
        this.queryType = queryType;
        this.arg = arg;
    }

    public PersistenceObject(Class resultClass, String querySql, Integer queryType, Object[] args) {
        this.resultClass = resultClass;
        this.querySql = querySql;
        this.queryType = queryType;
        this.args = args;
    }

    public PersistenceObject(String querySql, Integer queryType, Integer limitResults) {
        this.querySql = querySql;
        this.queryType = queryType;
        this.limitResults = limitResults;
    }

    public PersistenceObject(String querySql, Integer queryType, Object arg, Integer limitResults) {
        this.querySql = querySql;
        this.queryType = queryType;
        this.arg = arg;
        this.limitResults = limitResults;
    }

    public PersistenceObject(String querySql, Integer queryType, Object[] args, Integer limitResults) {
        this.querySql = querySql;
        this.queryType = queryType;
        this.args = args;
        this.limitResults = limitResults;
    }

    public PersistenceObject(Class resultClass, String querySql, Integer queryType, Integer limitResults) {
        this.resultClass = resultClass;
        this.querySql = querySql;
        this.queryType = queryType;
        this.limitResults = limitResults;
    }

    public PersistenceObject(Class resultClass, String querySql, Integer queryType, Object arg, Integer limitResults) {
        this.resultClass = resultClass;
        this.querySql = querySql;
        this.queryType = queryType;
        this.arg = arg;
        this.limitResults = limitResults;
    }

    public PersistenceObject(Class resultClass, String querySql, Integer queryType, Object[] args, Integer limitResults) {
        this.resultClass = resultClass;
        this.querySql = querySql;
        this.queryType = queryType;
        this.args = args;
        this.limitResults = limitResults;
    }

    public PersistenceObject(String sqlResultSetMapping, String querySql, Integer queryType) {
        this.sqlResultSetMapping = sqlResultSetMapping;
        this.querySql = querySql;
        this.queryType = queryType;
    }

    public PersistenceObject(String sqlResultSetMapping, String querySql, Integer queryType, Object arg) {
        this.sqlResultSetMapping = sqlResultSetMapping;
        this.querySql = querySql;
        this.queryType = queryType;
        this.arg = arg;
    }

    public PersistenceObject(String sqlResultSetMapping, String querySql, Integer queryType, Object[] args) {
        this.sqlResultSetMapping = sqlResultSetMapping;
        this.querySql = querySql;
        this.queryType = queryType;
        this.args = args;
    }

    public Class getResultClass() {
        return resultClass;
    }

    public void setResultClass(Class resultClass) {
        this.resultClass = resultClass;
    }

    public String getQuerySql() {
        return querySql;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public Object getArg() {
        return arg;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getSqlResultSetMapping() {
        return sqlResultSetMapping;
    }

    public void setSqlResultSetMapping(String sqlResultSetMapping) {
        this.sqlResultSetMapping = sqlResultSetMapping;
    }

    public Integer getLimitResults() {
        return limitResults;
    }

    public void setLimitResults(Integer limitResults) {
        this.limitResults = limitResults;
    }

}
