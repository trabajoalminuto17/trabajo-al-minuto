/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.facade;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Menu;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import java.util.List;

/**
 *
 * @author BASP
 */
public interface UsuarioFacade {

    public Usuario getUsuarioByEmail(String email) throws BusinessException;

    public Boolean validarCredenciales(Usuario u) throws BusinessException;

    public Boolean cambiarClave(Usuario u) throws BusinessException;

    public List<Usuario> getUsuariosByEmpresa(Long id) throws BusinessException;

    public Usuario findUsuario(Long pk) throws BusinessException;

    public Usuario updateUsuario(Usuario u) throws BusinessException;

    public List<Usuario> getUsuariosByOferta(Long id) throws BusinessException;

    public UsuarioHasOferta updateUsuarioHasOferta(UsuarioHasOferta uho) throws BusinessException;
    
    public List<Menu> getMenusByRol(Long id) throws BusinessException;

}
