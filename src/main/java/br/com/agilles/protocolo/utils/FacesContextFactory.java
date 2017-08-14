package br.com.agilles.protocolo.utils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextFactory {
    @Produces
    @RequestScoped
    public FacesContext create() {
        return FacesContext.getCurrentInstance();
    }
}
