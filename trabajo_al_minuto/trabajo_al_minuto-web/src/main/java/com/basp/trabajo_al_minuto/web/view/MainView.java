/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class MainView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private MenuModel menu;

    @PostConstruct
    public void init() {
        menu = getMenus();
    }

//    @Getter and Setter
    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

}
